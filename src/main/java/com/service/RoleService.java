package com.service;

import com.dto.RoleDTO;
import com.entities.ERole;

public interface RoleService {
	RoleDTO findByName(ERole name);
}
