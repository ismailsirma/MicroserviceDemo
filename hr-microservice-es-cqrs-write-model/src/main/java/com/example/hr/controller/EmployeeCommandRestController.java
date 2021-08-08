package com.example.hr.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.dto.UpdateEmployeeSalaryRequest;
import com.example.hr.entity.Employee;
import com.example.hr.eventsource.EmployeeEvent;
import com.example.hr.service.EmployeeCommandService;
import com.example.validation.TcKimlikNo;

@RestController
@RequestMapping("employees")
@CrossOrigin
@Validated
public class EmployeeCommandRestController {
	@Autowired
	private EmployeeCommandService empSrv;
	
	@PostMapping
	public EmployeeEvent createEmployee(@RequestBody @Validated Employee employee) {
		return empSrv.addEmployee(employee);
	}

	@PutMapping("{identity}")
	public EmployeeEvent updateEmployee(@RequestBody @Validated UpdateEmployeeSalaryRequest employee,
			@PathVariable @TcKimlikNo String identity) {
		return empSrv.updateEmployee(employee, identity);
	}

	@PatchMapping("{identity}")
	public EmployeeEvent patchEmployee(@RequestBody Map<String, Object> employee, @PathVariable String identity) {
		return empSrv.patchEmployee(employee, identity);
	}

	@DeleteMapping("{identity}")
	public EmployeeEvent deleteEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
		return empSrv.removeByIdentity(identity);
	}
}
