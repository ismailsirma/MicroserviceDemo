package com.example.hr.service;

import java.util.List;

import com.example.hr.entity.Employee;

public interface EmployeeQueryService {
	public Employee findByIdentity(String identity);

	public List<Employee> findEmployees(int page, int size);
}
