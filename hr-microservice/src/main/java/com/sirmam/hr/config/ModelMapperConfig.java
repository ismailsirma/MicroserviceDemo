package com.sirmam.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sirmam.hr.document.EmployeeDocument;
import com.sirmam.hr.domain.Employee;
import com.sirmam.hr.domain.FiatCurrency;
import com.sirmam.hr.domain.TcKimlikNo;
import com.sirmam.hr.dto.EmployeeKafkaEvent;
import com.sirmam.hr.entity.EmployeeEntity;
import com.sirmam.hr.events.EmployeeEvent;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(employeeDocument2Employee, EmployeeDocument.class, Employee.class);
		mapper.addConverter(employee2EmployeeDocument, Employee.class, EmployeeDocument.class);
		mapper.addConverter(employeeEvent2EmployeeKafkaEvent, EmployeeEvent.class, EmployeeKafkaEvent.class);
		
		mapper.addConverter(employeeEntity2Employee, EmployeeEntity.class, Employee.class);
		mapper.addConverter(employee2EmployeeEntity, Employee.class, EmployeeEntity.class);
		
		return mapper;
	}
	
	// EmployeeDocument --> Employee
	private static final Converter<EmployeeDocument,Employee> employeeDocument2Employee 
		= context -> {
			var empDoc = context.getSource();
			return new Employee.Builder(TcKimlikNo.valueOf(empDoc.getKimlikNo()))
							.fullname(empDoc.getFullname())
							.iban(empDoc.getIban())
							.salary(empDoc.getSalary(),FiatCurrency.TRY)
							.birthYear(empDoc.getBirthYear())
							.jobType(empDoc.getJobType().name())
							.department(empDoc.getDepartment().name())
							.photo(empDoc.getPhoto().getBytes())
							.build();
		};
		
	// Employee --> EmployeeDocument
		private static final Converter<Employee,EmployeeDocument> employee2EmployeeDocument 
		= context -> {
			var empDoc = new EmployeeDocument();
			var employee = context.getSource();
			empDoc.setKimlikNo(employee.getKimlikNo().getValue());
			var fullname = employee.getFullname();
			empDoc.setFullname(fullname.getFirstName() + " "+ fullname.getLastName());
			empDoc.setSalary(employee.getSalary().getValue());
			empDoc.setIban(employee.getIban().getValue());
			empDoc.setDepartment(employee.getDepartment());
			empDoc.setJobType(employee.getJobType());
			empDoc.setPhoto(new String(employee.getPhoto().getData()));
			empDoc.setBirthYear(employee.getBirthYear().getValue());
			return empDoc;
		};
	
		
	// EmployeeEvent --> EmployeeKafkaEvent
		private static final Converter<EmployeeEvent,EmployeeKafkaEvent> employeeEvent2EmployeeKafkaEvent 
		= context -> {
			var employeeEvent = context.getSource();
			var employeeKafkaEvent = new EmployeeKafkaEvent(employeeEvent.getKimlikNo().getValue());
			
			return employeeKafkaEvent;
		};		
		
		// EmployeeEntity --> Employee
		private static final Converter<EmployeeEntity,Employee> employeeEntity2Employee 
			= context -> {
				var empEntity = context.getSource();
				return new Employee.Builder(TcKimlikNo.valueOf(empEntity.getKimlikNo()))
								.fullname(empEntity.getFullname())
								.iban(empEntity.getIban())
								.salary(empEntity.getSalary(),FiatCurrency.TRY)
								.birthYear(empEntity.getBirthYear())
								.jobType(empEntity.getJobType().name())
								.department(empEntity.getDepartment().name())
								.photo(empEntity.getPhoto())
								.build();
			};
			
		// Employee --> EmployeeEntity
			private static final Converter<Employee,EmployeeEntity> employee2EmployeeEntity 
			= context -> {
				var empEntity = new EmployeeEntity();
				var employee = context.getSource();
				empEntity.setKimlikNo(employee.getKimlikNo().getValue());
				var fullname = employee.getFullname();
				empEntity.setFullname(fullname.getFirstName() + " "+ fullname.getLastName());
				empEntity.setSalary(employee.getSalary().getValue());
				empEntity.setIban(employee.getIban().getValue());
				empEntity.setDepartment(employee.getDepartment());
				empEntity.setJobType(employee.getJobType());
				empEntity.setPhoto(employee.getPhoto().getData());
				empEntity.setBirthYear(employee.getBirthYear().getValue());
				return empEntity;
			};		
		
}
