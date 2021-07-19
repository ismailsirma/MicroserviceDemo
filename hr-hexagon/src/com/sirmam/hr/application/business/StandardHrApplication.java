package com.sirmam.hr.application.business;

import java.util.Optional;

import com.sirmam.hr.application.HrApplication;
import com.sirmam.hr.domain.Employee;
import com.sirmam.hr.domain.TcKimlikNo;
import com.sirmam.hr.events.EmployeeFiredEvent;
import com.sirmam.hr.events.EmployeeHiredEvent;
import com.sirmam.hr.infrastructure.EventPublisher;
import com.sirmam.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {

	// Loose Coupling -> EmployeeRepository is an interface
	// Dependency Injection -> Constructor Injection 
	private EmployeeRepository employeeRepository;
	
	private EventPublisher eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getKimlikNo();
		if(employeeRepository.existsByIdentity(identity)) {
			throw new IllegalArgumentException("Employee already exists!");
		}
		Employee emp = employeeRepository.persist(employee);
		eventPublisher.publish(new EmployeeHiredEvent(identity));
		return emp;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo kimlikNo) {
		Optional<Employee> emp = employeeRepository.removeByIdentity(kimlikNo);
		if(emp.isPresent()) {
			eventPublisher.publish(new EmployeeFiredEvent(kimlikNo));
		}
		
		return emp;
	}

}
