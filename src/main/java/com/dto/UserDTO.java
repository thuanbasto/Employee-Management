package com.dto;

import java.util.Set;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserDTO {
	
	private Integer id;
	
	@NotBlank
	@Size(min = 6, max = 255, message = "Username should be 6 - 255 characters")
	private String username;

	@NotBlank
	@Size(min = 6, max = 255, message = "Password should be 6 - 255 characters")
	private String password;

	private Set<String> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<String> role) {
		this.roles = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
