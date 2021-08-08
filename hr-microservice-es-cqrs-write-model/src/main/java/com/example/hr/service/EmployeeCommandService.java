package com.example.hr.service;

import java.util.Map;

import com.example.hr.dto.UpdateEmployeeSalaryRequest;
import com.example.hr.entity.Employee;
import com.example.hr.eventsource.EmployeeEvent;

public interface EmployeeCommandService {
	public EmployeeEvent addEmployee(Employee employee);

	public EmployeeEvent updateEmployee(UpdateEmployeeSalaryRequest employee, String identity);

	public EmployeeEvent patchEmployee(Map<String, Object> employee, String identity);

	public EmployeeEvent removeByIdentity(String identity);

}
