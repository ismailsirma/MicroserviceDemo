package com.sirmam.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sirmam.hr.application.HrApplication;
import com.sirmam.hr.application.business.StandardHrApplication;
import com.sirmam.hr.infrastructure.EventPublisher;
import com.sirmam.hr.repository.EmployeeRepository;

@Configuration
public class ApplicationConfig {

	@Bean
	public HrApplication hrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		return new StandardHrApplication(employeeRepository, eventPublisher);
	}
}
