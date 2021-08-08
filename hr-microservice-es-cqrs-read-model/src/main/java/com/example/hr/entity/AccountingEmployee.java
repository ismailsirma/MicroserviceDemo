package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.example.validation.TcKimlikNo;

@Entity
@Table(name = "accemp")
public class AccountingEmployee {
	@Column(name = "tckimlikno")
	@TcKimlikNo
	@Id
	private String identity;
	private String iban;
	@Column(name = "maas")
	@Min(2700)
	private double salary;
	private boolean fulltime;

	public AccountingEmployee() {
	}

	public AccountingEmployee(String identity, String iban, @Min(2700) double salary, boolean fulltime) {
		this.identity = identity;
		this.iban = iban;
		this.salary = salary;
		this.fulltime = fulltime;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
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

	@Override
	public String toString() {
		return "AccountingEmployee [identity=" + identity + ", iban=" + iban + ", salary=" + salary + ", fulltime="
				+ fulltime + "]";
	}

}
