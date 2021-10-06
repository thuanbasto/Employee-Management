package com.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.dto.MessageResponse;
import com.exception.exceptionclass.ForbiddenException;

@RestControllerAdvice
public class CommonException {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public Map<String, String> handleMaxSizeException(MaxUploadSizeExceededException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("file", "File too large. Can upload file with 1MB");
		return errors;
	}

	@ResponseStatus(value = HttpStatus.CONFLICT) // 409
	@ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		if (ex.getCause() instanceof ConstraintViolationException) {
			String errorMessage = "";
			if (ex.getRootCause().getMessage().contains("Duplicate")) {
				errorMessage = "Cannot add this item, duplidate item name";
			} else if (ex.getRootCause().getMessage().contains("Cannot delete")) {
				errorMessage = "Cannot delete this item, because this item exist child item";
			} else {
				errorMessage = ex.getRootCause().getMessage();
			}
			errors.put("SQL", errorMessage);
		} else {
			errors.put("SQL", ex.getRootCause().getMessage());
		}
		
		return errors;
    }
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	public MessageResponse handleForbiddenException(ForbiddenException ex) {
		return new MessageResponse(ex.getMessage(), 403, false);
	}
}
