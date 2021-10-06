package com.service;

import com.dto.SignUpDTO;
import com.dto.UserDTO;

public interface UserService {
	UserDTO findByUsername(String username);

	Boolean existsByUsername(String username);
	
	void signup(SignUpDTO signUpDTO);
}
