package com.example.hr.service.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends HrBaseException {
	private final String identity;

	public EmployeeNotFoundException(String message, String i18n, String debug, String identity) {
		super(message, i18n, debug);
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}
}
