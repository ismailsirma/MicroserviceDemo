package com.sirmam.hr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sirmam.hr.domain.BirthYear;
import com.sirmam.hr.domain.Department;
import com.sirmam.hr.domain.FiatCurrency;
import com.sirmam.hr.domain.FullName;
import com.sirmam.hr.domain.Iban;
import com.sirmam.hr.domain.JobType;
import com.sirmam.hr.domain.Money;
import com.sirmam.hr.domain.Photo;
import com.sirmam.hr.domain.TcKimlikNo;

@Document(collection = "employees")
public class EmployeeDocument {
	@Id
	private String kimlikNo;
	private String fullname;
	private double salary;
	private FiatCurrency currency;
	private String iban;
	private int birthYear;
	private Department department;
	private JobType jobType;
	private String photo;
	
	public EmployeeDocument() {
	}

	
	
	public String getKimlikNo() {
		return kimlikNo;
	}



	public String getFullname() {
		return fullname;
	}



	public double getSalary() {
		return salary;
	}



	public FiatCurrency getCurrency() {
		return currency;
	}



	public String getIban() {
		return iban;
	}



	public int getBirthYear() {
		return birthYear;
	}



	public Department getDepartment() {
		return department;
	}



	public JobType getJobType() {
		return jobType;
	}



	public String getPhoto() {
		return photo;
	}



	public void setKimlikNo(String kimlikNo) {
		this.kimlikNo = kimlikNo;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public void setPhoto(String photo) {
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
		EmployeeDocument other = (EmployeeDocument) obj;
		if (kimlikNo == null) {
			if (other.kimlikNo != null)
				return false;
		} else if (!kimlikNo.equals(other.kimlikNo))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "EmployeeDocument [kimlikNo=" + kimlikNo + ", fullname=" + fullname + ", salary=" + salary
				+ ", currency=" + currency + ", iban=" + iban + ", birthYear=" + birthYear + ", department="
				+ department + ", jobType=" + jobType + "]";
	}
	
	
	
}
