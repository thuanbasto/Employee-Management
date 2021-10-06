package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MessageResponse;
import com.exception.exceptionclass.DuplicateDepartmentName;
import com.exception.exceptionclass.ExistDepartmentChirldren;
import com.exception.exceptionclass.NoExistDepartment;

@ControllerAdvice
@RestController
public class DepartmentException {
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DuplicateDepartmentName.class)
	public MessageResponse handlerDuplicateDepartmentName() {
		MessageResponse err = new MessageResponse("Duplicate department name", 409, false);
		return err;
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ExistDepartmentChirldren.class)
	public MessageResponse handlerExistDepartmentChirldren(ExistDepartmentChirldren ex) {
		MessageResponse err = new MessageResponse("Department " + ex.getDepartmentName() + " has employee. Cannot delete", 409, false);
		return err;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoExistDepartment.class)
	public MessageResponse handlerNoExistDepartment(NoExistDepartment ex) {
		MessageResponse err = new MessageResponse("Has department was deleted", 404, false);
		return err;
	}
}
