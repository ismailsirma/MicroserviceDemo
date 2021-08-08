package com.example.hr.service.exception;

@SuppressWarnings("serial")
public class HrBaseException extends RuntimeException {
	private final String i18n;
	private final String debug;

	public HrBaseException(String message, String i18n, String debug) {
		super(message);
		this.i18n = i18n;
		this.debug = debug;
	}

	public String getI18n() {
		return i18n;
	}

	public String getDebug() {
		return debug;
	}

}
