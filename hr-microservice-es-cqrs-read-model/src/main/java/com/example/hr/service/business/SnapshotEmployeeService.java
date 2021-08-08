package com.example.hr.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.hr.entity.AccountingEmployee;
import com.example.hr.entity.Employee;
import com.example.hr.eventsource.EmployeeEvent;
import com.example.hr.repository.AccountingEmployeeSnapshotRepository;
import com.example.hr.repository.EmployeeSnapshotRepository;

@Service
public class SnapshotEmployeeService {
	@Autowired
	private EmployeeSnapshotRepository employeeSnapshotRepository;
	@Autowired
	private AccountingEmployeeSnapshotRepository accountingEmployeeSnapshotRepository;
	
	@KafkaListener(topics = "employees")
	public void listenEmployeeCreatedEvents(EmployeeEvent event) {
		var employee = new Employee();
		//TODO: Event -> Employee
		employeeSnapshotRepository.save(employee);
		
		// Projection
		var accEmp = new AccountingEmployee();
		//TODO: project Employee -> AccountingEmployee
		accountingEmployeeSnapshotRepository.save(accEmp);
		
	}

	
}
