package com.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DepartmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer department_id;

	@Length(min = 1, max = 100, message = "Department name should be 1 - 100 characters")
	private String departmentName;
	
	private Boolean deletedFlag;
	
	@JsonIgnore
	private List<EmployeeDTO> employees;
	
	
	public DepartmentDTO() {
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

}