package com.sirmam.hr.service;

import java.util.Map;

import com.sirmam.hr.dto.UpdateEmployeeSalaryRequest;
import com.sirmam.hr.entity.Employee;
import com.sirmam.hr.eventsource.EmployeeEvent;

public interface EmployeeCommandService {
	public EmployeeEvent addEmployee(Employee employee);

	public EmployeeEvent updateEmployee(UpdateEmployeeSalaryRequest employee, String identity);

	public EmployeeEvent patchEmployee(Map<String, Object> employee, String identity);

	public EmployeeEvent removeByIdentity(String identity);

}
