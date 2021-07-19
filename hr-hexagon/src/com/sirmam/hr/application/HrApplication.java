package com.sirmam.hr.application;

import java.util.Optional;

import com.sirmam.hr.domain.Employee;
import com.sirmam.hr.domain.TcKimlikNo;

public interface HrApplication {
	Employee hireEmployee(Employee employee);
	Optional<Employee> fireEmployee(TcKimlikNo kimlikNo);
}
