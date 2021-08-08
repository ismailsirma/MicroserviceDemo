package com.example.hr.dto;

import javax.validation.constraints.Min;

import com.example.hr.entity.Department;
import com.example.validation.Iban;

public class UpdateEmployeeRequest {
	@Iban
	private String iban;
	@Min(2700)
	private double salary;
	private boolean fulltime;
	private byte[] photo;
	private Department department;

	public UpdateEmployeeRequest() {
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

	public boolean isFulltime() {
		return fulltime;
	}

	public void setFulltime(boolean fulltime) {
		this.fulltime = fulltime;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeRequest [iban=" + iban + ", salary=" + salary + ", fulltime=" + fulltime + ", department="
				+ department + "]";
	}

}
