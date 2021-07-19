package com.sirmam.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.sirmam.hr.dto.FireEmployeeResponse;
import com.sirmam.hr.dto.HireEmployeeRequest;
import com.sirmam.hr.dto.HireEmployeeResponse;
import com.sirmam.hr.service.HrService;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin("*")
// Open API Methods to outside clients 
public class HrController {
	
	@Autowired
	private HrService hrService;
	
	// ACL : Anti-Corruption Layer
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	// DELETE /employees/1111111110
	@DeleteMapping("{identity}")
	public FireEmployeeResponse hireEmployee(@PathVariable("identity") String identity) {
		return hrService.fireEmployee(identity);
	}
	
}
