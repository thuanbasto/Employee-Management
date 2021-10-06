package com.exception.exceptionclass;

public class DuplicateDepartmentName extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DuplicateDepartmentName() {
	}
	
	public DuplicateDepartmentName(String message) {
		super(message);
	}
}
