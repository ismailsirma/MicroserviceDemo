package com.example.hr.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.entity.Employee;

public interface EmployeeRepository 
    extends JpaRepository<Employee, String>{
	List<Employee> findTop10ByOrderBySalaryDesc();
	@Query(value="select * from employees order by maas desc limit 10",
			nativeQuery = true)
	List<Employee> enfazlaMaasAlanIlkOnuGetir();
	@Query(value="select e from Employee e order by e.salary desc",
			nativeQuery = false)
	List<Employee> enYuksek10MaasliCalisan(
			Pageable page);
	
}
