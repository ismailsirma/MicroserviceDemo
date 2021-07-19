package com.sirmam.hr.dto;

public class FireEmployeeResponse {

	private String status;
	private String reason;

	public FireEmployeeResponse(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public FireEmployeeResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	
	
}
