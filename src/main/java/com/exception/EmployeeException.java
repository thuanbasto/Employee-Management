package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MessageResponse;
import com.exception.exceptionclass.NoExistEmployee;

@ControllerAdvice
@RestController
public class EmployeeException {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoExistEmployee.class)
	public MessageResponse handlerNoExistEmployee(NoExistEmployee ex) {
		MessageResponse err = new MessageResponse("Has employee was deleted", 404, false);
		return err;
	}
}
