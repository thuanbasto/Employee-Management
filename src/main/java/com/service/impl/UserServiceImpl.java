package com.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.converter.UserConverter;
import com.dto.SignUpDTO;
import com.dto.UserDTO;
import com.entities.ERole;
import com.entities.Role;
import com.entities.User;
import com.exception.exceptionclass.DuplicateUsername;
import com.exception.exceptionclass.PasswordDoesNotMatch;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserConverter userConverter;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername("username");
		if (user.isPresent()) {
			return userConverter.toDTO(user.get());
		}
		return null;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public void signup(SignUpDTO signUpDTO) {
		if (userRepository.existsByUsername(signUpDTO.getUsername())) {
			throw new DuplicateUsername();
		}
		if (!signUpDTO.getPassword().equals(signUpDTO.getPasswordConfirm())) {
			throw new PasswordDoesNotMatch();
		}
		
		User user = new User(signUpDTO.getUsername(), passwordEncoder.encode(signUpDTO.getPassword()));
				
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
				
		user.setRoles(roles);
		userRepository.save(user);
	}
}
