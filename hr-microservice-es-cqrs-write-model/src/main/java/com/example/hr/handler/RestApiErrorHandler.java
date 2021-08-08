package com.example.hr.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.service.exception.DuplicateEmployeeException;
import com.example.hr.service.exception.EmployeeNotFoundException;
import com.example.hr.service.exception.HrBaseException;

@RestControllerAdvice
public class RestApiErrorHandler {
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("|"));
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", e.getMessage());
		response.put("i18n", e.getI18n());
		response.put("debug", e.getDebug());
		response.put("identity", e.getIdentity());
		return response;
	}

	@ExceptionHandler(DuplicateEmployeeException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleDuplicateEmployeeException(DuplicateEmployeeException e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", e.getMessage());
		response.put("i18n", e.getI18n());
		response.put("debug", e.getDebug());
		response.put("identity", e.getIdentity());
		return response;
	}

	@ExceptionHandler(HrBaseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleHrBaseException(HrBaseException e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", e.getMessage());
		response.put("i18n", e.getI18n());
		response.put("debug", e.getDebug());
		return response;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
	public Map<String, Object> handleAllOtherExceptions(Throwable e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", e.getMessage());
		response.put("i18n", e.getClass().getSimpleName());
		response.put("debug", e.getClass().getSimpleName());
		return response;
	}
}
