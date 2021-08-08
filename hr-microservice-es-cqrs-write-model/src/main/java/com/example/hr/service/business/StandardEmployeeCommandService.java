package com.example.hr.service.business;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.dto.UpdateEmployeeSalaryRequest;
import com.example.hr.entity.Employee;
import com.example.hr.eventsource.EmployeeCreatedEvent;
import com.example.hr.eventsource.EmployeeDeletedEvent;
import com.example.hr.eventsource.EmployeeEvent;
import com.example.hr.eventsource.EmployeeSalaryUpdatedEvent;
import com.example.hr.repository.EmployeeEventRepository;
import com.example.hr.service.EmployeeCommandService;
import com.example.hr.service.exception.DuplicateEmployeeException;
import com.example.hr.service.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Write Model -> Event
@Service
public class StandardEmployeeCommandService implements EmployeeCommandService {
	private static final String[] UPDATABLE_FIELDS = { "salary", "iban", "photo", "department", "fulltime" };

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private ObjectMapper objectMapper;
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
		try {
			kafkaTemplate.send("employees", objectMapper.writeValueAsString(event));
			employeeEventRepository.save(event);
		} catch (JsonProcessingException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return event;
	}

	@Override
	public EmployeeEvent updateEmployee(UpdateEmployeeSalaryRequest employee, String identity) {

		if (!employeeEventRepository.existsByIdentity(identity))
			throw new EmployeeNotFoundException("Cannot find employee to update", "110",
					"ad546e2b-cfd8-4ba2-ace9-9af82d14b9d9", identity);
		var employeeSalaryUpdatedEvent = new EmployeeSalaryUpdatedEvent();
		try {
			kafkaTemplate.send("employees", objectMapper.writeValueAsString(employeeSalaryUpdatedEvent));
			employeeEventRepository.save(employeeSalaryUpdatedEvent);
		} catch (JsonProcessingException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return employeeSalaryUpdatedEvent;
	}

	@Override
	public EmployeeEvent patchEmployee(Map<String, Object> employee, String identity) {
		if (!employeeEventRepository.existsByIdentity(identity))
			throw new EmployeeNotFoundException("Cannot find employee to patch", "120",
					"220f5b38-38d2-42dd-8066-95f6390f50a5", identity);
		Arrays.stream(UPDATABLE_FIELDS).forEach(field -> {
			if (employee.containsKey(field)) {
				// TODO: create event then save the event to the mongodb
				// publish event
			}
		});
		// TODO: return the business event
		return null;
	}

	@Override
	public EmployeeEvent removeByIdentity(String identity) {
		if (!employeeEventRepository.existsByIdentity(identity))
			throw new EmployeeNotFoundException("Cannot find employee to delete", "130",
					"accc58f8-4b11-4772-ba07-6829da831468", identity);
		var employeeDeletedEvent = new EmployeeDeletedEvent();
		try {
			kafkaTemplate.send("employees", objectMapper.writeValueAsString(employeeDeletedEvent));
			employeeEventRepository.save(employeeDeletedEvent);
		} catch (JsonProcessingException e) {
			System.err.println("Error: " + e.getMessage());
		}		return employeeDeletedEvent;
	}

}
