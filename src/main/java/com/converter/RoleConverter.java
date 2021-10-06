package com.converter;

import org.springframework.stereotype.Component;

import com.dto.RoleDTO;
import com.entities.Role;

@Component
public class RoleConverter {
	public RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setName(role.getName());
		return roleDTO;
	}
	
	public Role toEntity(RoleDTO roleDTO) {
		Role role = new Role();
		role.setName(roleDTO.getName());
		return role;
	}
}
