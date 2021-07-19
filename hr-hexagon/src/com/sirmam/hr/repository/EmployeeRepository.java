package com.sirmam.hr.repository;

import java.util.Optional;

import com.sirmam.hr.domain.Employee;
import com.sirmam.hr.domain.TcKimlikNo;

public interface EmployeeRepository {

	boolean existsByIdentity(TcKimlikNo identity);

	Employee persist(Employee employee);

	Optional<Employee> removeByIdentity(TcKimlikNo kimlikNo);
	
}
