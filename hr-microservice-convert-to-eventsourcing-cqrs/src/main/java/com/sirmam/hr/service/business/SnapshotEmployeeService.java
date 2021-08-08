package com.sirmam.hr.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.sirmam.hr.entity.Employee;
import com.sirmam.hr.eventsource.EmployeeCreatedEvent;
import com.sirmam.hr.eventsource.EmployeeDeletedEvent;
import com.sirmam.hr.eventsource.EmployeeSalaryUpdatedEvent;
import com.sirmam.hr.repository.EmployeeSnapshotRepository;

@Service
public class SnapshotEmployeeService {
	@Autowired
	private EmployeeSnapshotRepository employeeSnapshotRepository;
	
	@EventListener
	public void listenEmployeeCreatedEvents(EmployeeCreatedEvent event) {
		var employee = new Employee();
		//TODO: Event -> Employee
		employeeSnapshotRepository.save(employee);
	}
	
	@EventListener
	public void listenEmployeeSalaryUpdatedEvents(EmployeeSalaryUpdatedEvent event) {
		var employee = new Employee();
		//TODO: Event -> update Employee's salary
		employeeSnapshotRepository.save(employee);		
	}

	@EventListener
	public void listenEmployeeDeletedEvents(EmployeeDeletedEvent event) {
		employeeSnapshotRepository.deleteById(event.getIdentity());
	}
	
}
