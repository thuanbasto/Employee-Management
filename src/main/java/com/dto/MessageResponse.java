package com.dto;

public class MessageResponse {
	private String message;
	private int statusCode;
	private Boolean isSuccess;

	public MessageResponse(String message) {
		this.message = message;
	}
	
	public MessageResponse(String message, int statusCode, Boolean isSuccess) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}