package com.sirmam.hr.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sirmam.hr.domain.Department;
import com.sirmam.hr.domain.JobType;
import com.sirmam.hr.validation.Iban;
import com.sirmam.hr.validation.TcKimlikNo;

public class HireEmployeeRequest {
	
	@TcKimlikNo
	private String identity;
	@Pattern(regexp="^[A-Z][a-z]+$")
	private String firstName;
	@Pattern(regexp="^[A-Z][a-z]+$")
	private String lastName;
	@Iban
	private String iban;
	@Min(3_000)
	private double salary;
	private Department department;
	private JobType type;
	@Min(1950)
	private int birthYear;
	private String photo; // Base64 Encoded
	
	public HireEmployeeRequest() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "HireEmployeeRequest [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", iban=" + iban + ", salary=" + salary + ", department=" + department + ", type=" + type
				+ ", birthYear=" + birthYear + "]";
	}
	
}
