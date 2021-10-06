package com.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.RoleConverter;
import com.dto.RoleDTO;
import com.entities.ERole;
import com.entities.Role;
import com.repository.RoleRepository;
import com.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleConverter roleConverter;

	@Override
	public RoleDTO findByName(ERole name) {
		Optional<Role> role = roleRepository.findByName(name);
		if (role.isPresent()) {
			return roleConverter.toDTO(role.get());
		}
		return null;
	}

}
