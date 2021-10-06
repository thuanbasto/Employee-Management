package com.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class HeartId implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer user_id;

	private Integer employee_id;

	public HeartId(Integer user_id, Integer employee_id) {
		this.user_id = user_id;
		this.employee_id = employee_id;
	}

	public HeartId() {
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

}
