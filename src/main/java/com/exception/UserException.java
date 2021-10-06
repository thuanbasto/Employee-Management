package com.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exception.exceptionclass.DuplicateUsername;
import com.exception.exceptionclass.PasswordDoesNotMatch;

@RestControllerAdvice
public class UserException {
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DuplicateUsername.class)
	public Map<String, String> handlerDuplicateUsername(DuplicateUsername ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("username", "Username is already taken!");
		return errors;
//		MessageResponse err = new MessageResponse("Username is already taken!", 409, false);
//		return err;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PasswordDoesNotMatch.class)
	public Map<String, String> handlerPasswordDoesNotMatch(PasswordDoesNotMatch ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("passwordConfirm", "Password doesn't match!");
		return errors;
//		MessageResponse err = new MessageResponse("Password doesn't match!", 400, false);
//		return err;
	}
}
