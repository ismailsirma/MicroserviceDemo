package com.example.hr.eventsource;

public class EmployeeCreatedEvent extends EmployeeEvent {
	private String fullname;
	private String iban;
	private double salary;

	public EmployeeCreatedEvent() {
	}

	public EmployeeCreatedEvent(String eventId, String identity, String conversationId, String fullname, String iban,
			double salary) {
		super(eventId, identity, conversationId);
		this.fullname = fullname;
		this.iban = iban;
		this.salary = salary;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeCreatedEvent [fullname=" + fullname + ", iban=" + iban + ", salary=" + salary + ", eventId="
				+ eventId + ", identity=" + identity + ", conversationId=" + conversationId + "]";
	}

}
