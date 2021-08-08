package com.sirmam.hr.service.business;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sirmam.hr.dto.UpdateEmployeeSalaryRequest;
import com.sirmam.hr.entity.Employee;
import com.sirmam.hr.eventsource.EmployeeCreatedEvent;
import com.sirmam.hr.eventsource.EmployeeDeletedEvent;
import com.sirmam.hr.eventsource.EmployeeEvent;
import com.sirmam.hr.eventsource.EmployeeSalaryUpdatedEvent;
import com.sirmam.hr.repository.EmployeeEventRepository;
import com.sirmam.hr.service.EmployeeCommandService;
import com.sirmam.hr.service.exception.DuplicateEmployeeException;
import com.sirmam.hr.service.exception.EmployeeNotFoundException;

// Write Model -> Event
@Service
public class StandardEmployeeCommandService implements EmployeeCommandService {
	private static final String[] UPDATABLE_FIELDS = { "salary", "iban", "photo", "department", "fulltime" };

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private EmployeeEventRepository employeeEventRepository;
	
	@Value("${server.port}")
	private int port;


	@Override
	public EmployeeEvent addEmployee(Employee employee) {
		String identity = employee.getIdentity();
		if (employeeEventRepository.existsByIdentity(identity)) {
			throw new DuplicateEmployeeException("Employee already exists!", "100", // ==> i18n
					"1c94dd33-102c-4aea-b627-900a07215718", // ==> debug
					identity);
		}
		EmployeeCreatedEvent event = new EmployeeCreatedEvent();
		// TODO: populate event fields
		employeeEventRepository.save(event);
		publisher.publishEvent(event);
		return event;
	}

	@Override
	public EmployeeEvent updateEmployee(UpdateEmployeeSalaryRequest employee, String identity) {
		
		if (!employeeEventRepository.existsByIdentity(identity))
			throw new EmployeeNotFoundException("Cannot find employee to update", "110",
					"ad546e2b-cfd8-4ba2-ace9-9af82d14b9d9", identity);
		var employeeSalaryUpdatedEvent = new EmployeeSalaryUpdatedEvent();
		employeeEventRepository.save(employeeSalaryUpdatedEvent);
		publisher.publishEvent(employeeSalaryUpdatedEvent);
		return employeeSalaryUpdatedEvent;
	}

	@Override
	public EmployeeEvent patchEmployee(Map<String, Object> employee, String identity) {
		if (!employeeEventRepository.existsByIdentity(identity))
			throw new EmployeeNotFoundException("Cannot find employee to patch", "120",
					"220f5b38-38d2-42dd-8066-95f6390f50a5", identity);
		Arrays.stream(UPDATABLE_FIELDS).forEach(field -> {
			if (employee.containsKey(field)) {
				//TODO: create event then save the event to the mongodb
				//publish event
			}
		});
		//TODO: return the business event
		return null;
	}

	@Override
	@Transactional
	public EmployeeEvent removeByIdentity(String identity) {
		if (!employeeEventRepository.existsByIdentity(identity))
			throw new EmployeeNotFoundException("Cannot find employee to delete", "130",
					"accc58f8-4b11-4772-ba07-6829da831468", identity);
		var employeeDeletedEvent = new EmployeeDeletedEvent();
		employeeEventRepository.save(employeeDeletedEvent);
		publisher.publishEvent(employeeDeletedEvent);
		return employeeDeletedEvent;
	}

}
