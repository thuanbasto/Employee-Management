package com.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.UserDTO;
import com.entities.User;

@Component
public class UserConverter {
	
	@Autowired
	ModelMapper mapper;
	
	public User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		return user;
	}
	
	public UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		Set<String> roles = new HashSet<>();
 		user.getRoles().forEach(role -> roles.add(role.getName().name()));
 		userDTO.setRoles(roles);
		return userDTO;
	}
}
