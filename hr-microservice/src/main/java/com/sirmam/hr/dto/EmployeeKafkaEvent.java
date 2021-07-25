package com.sirmam.hr.dto;

public class EmployeeKafkaEvent {
	private String identity;

	public EmployeeKafkaEvent(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	@Override
	public String toString() {
		return "EmployeeKafkaEvent [identity=" + identity + "]";
	}
	
	
}
