package com.dto;

import com.entities.ERole;

public class RoleDTO {
	private Integer id;

	private ERole name;

	public RoleDTO() {

	}

	public RoleDTO(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
