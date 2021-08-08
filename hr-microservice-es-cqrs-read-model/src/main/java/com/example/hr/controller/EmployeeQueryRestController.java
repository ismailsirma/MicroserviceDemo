package com.example.hr.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.entity.Employee;
import com.example.hr.service.EmployeeQueryService;
import com.example.validation.TcKimlikNo;

@RestController
@RequestMapping("employees")
@CrossOrigin
@Validated
public class EmployeeQueryRestController {
	@Autowired
	private EmployeeQueryService empSrv;

	// /hr/api/v1/employees/12345678910
	@GetMapping("{identity}")
	public Employee findEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
		return empSrv.findByIdentity(identity);
	}

	// /hr/api/v1/employees?page=10&size=25
	@GetMapping
	public List<Employee> findEmployees(@RequestParam @Min(0) int page, @RequestParam @Max(25) int size) {
		return empSrv.findEmployees(page, size);
	}

}
