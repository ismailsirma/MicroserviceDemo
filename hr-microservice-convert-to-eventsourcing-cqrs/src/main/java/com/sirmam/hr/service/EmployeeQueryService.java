package com.sirmam.hr.service;

import java.util.List;

import com.sirmam.hr.entity.Employee;

public interface EmployeeQueryService {
	public Employee findByIdentity(String identity);

	public List<Employee> findEmployees(int page, int size);
}
