package com.sirmam.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.sirmam.hr.domain.Department;
import com.sirmam.hr.domain.FiatCurrency;
import com.sirmam.hr.domain.JobType;

@Entity
@Table(name="employees")
public class EmployeeEntity {
	
	//javax.persistence
	@Id
	@Column(name="identity")
	private String kimlikNo;
	@Column(name="fullname")
	private String fullname;
	private double salary;
	
	@Enumerated(EnumType.STRING)
	private FiatCurrency currency;
	private String iban;
	
	@Column(name="birth_year")
	private int birthYear;
	
	@Enumerated(EnumType.STRING)
	private Department department;
	
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	
	// large object
	@Lob
	@Column(columnDefinition="longblob")
	private byte[] photo;

	public EmployeeEntity() {
	}

	public String getKimlikNo() {
		return kimlikNo;
	}

	public void setKimlikNo(String kimlikNo) {
		this.kimlikNo = kimlikNo;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kimlikNo == null) ? 0 : kimlikNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		if (kimlikNo == null) {
			if (other.kimlikNo != null)
				return false;
		} else if (!kimlikNo.equals(other.kimlikNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [kimlikNo=" + kimlikNo + ", fullname=" + fullname + ", salary=" + salary + ", currency="
				+ currency + ", iban=" + iban + ", birthYear=" + birthYear + ", department=" + department + ", jobType="
				+ jobType + "]";
	}

	
	
}
