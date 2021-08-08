package com.example.hr.service.exception;

@SuppressWarnings("serial")
public class DuplicateEmployeeException 
                   extends HrBaseException {

	private final String identity;

	public DuplicateEmployeeException(String message, 
			String i18n, String debug, String identity) {
		super(message,i18n,debug);
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

}
