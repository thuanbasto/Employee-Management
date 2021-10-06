package com.exception.exceptionclass;

public class ExistDepartmentChirldren extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String departmentName; 
	
	public ExistDepartmentChirldren() {
	}
	
	public ExistDepartmentChirldren(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
