package com.sirmam.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.sirmam.hr.document.EmployeeDocument;
import com.sirmam.hr.domain.Employee;
import com.sirmam.hr.domain.TcKimlikNo;
import com.sirmam.hr.repository.EmployeeDocumentRepository;
import com.sirmam.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name= "database", havingValue= "mongo")
public class MongoRepositoryEmployeeAdapter implements EmployeeRepository {

	// object adapter pattern
	@Autowired
	private EmployeeDocumentRepository employeeDocumentRepository;

	// low level database interactions
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		
		return employeeDocumentRepository.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		// Employee --> EmployeeDocument
		var employeeDocument = mapper.map(employee, EmployeeDocument.class);
		var emp = employeeDocumentRepository.save(employeeDocument);
		// EmployeeDocument -> Employee
		return mapper.map(emp, Employee.class);
	}

	@Override
	public Optional<Employee> removeByIdentity(TcKimlikNo kimlikNo) {
		var employeeDocument = employeeDocumentRepository.findById(kimlikNo.getValue());
		if(employeeDocument.isEmpty())
			return Optional.empty();
		EmployeeDocument empDoc = employeeDocument.get();
		employeeDocumentRepository.delete(empDoc);
		// EmployeeDocument --> Employee
		var employee = mapper.map(empDoc, Employee.class);
		return Optional.of(employee);
	}
}
