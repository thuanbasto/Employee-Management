package com.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class SignUpDTO {
	private Integer id;
	
	@NotBlank
	@Size(min = 6, max = 255, message = "Username should be 6 - 255 characters")
	private String username;

	@NotBlank
	@Size(min = 6, max = 255, message = "Password should be 6 - 255 characters")
	private String password;
	
	@NotBlank
	@Size(min = 6, max = 255, message = "Password confirm should be 6 - 255 characters")
	private String passwordConfirm;
	
	public SignUpDTO() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
}
