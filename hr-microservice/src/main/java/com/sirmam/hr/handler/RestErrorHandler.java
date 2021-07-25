package com.sirmam.hr.handler;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sirmam.hr.dto.RestErrorMessage;

@RestControllerAdvice
public class RestErrorHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleException(Exception e) {
		return new RestErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return new RestErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleConstraintViolationException(ConstraintViolationException e) {
		var violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining("|"));
		
		return new RestErrorMessage(violations);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		var violations = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
		
		return new RestErrorMessage(violations);
	}
	
}
