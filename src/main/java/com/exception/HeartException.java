package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dto.MessageResponse;
import com.exception.exceptionclass.NoExistHeart;

@RestControllerAdvice
public class HeartException {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoExistHeart.class)
	public MessageResponse handlerNoExistEmployee(NoExistHeart ex) {
		MessageResponse err = new MessageResponse("Heart was not found", 404, false);
		return err;
	}
}
