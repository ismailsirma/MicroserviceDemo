package com.sirmam.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sirmam.hr.document.EmployeeDocument;
import com.sirmam.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String> {
	List<EmployeeDocument> findAllByBirthYearBetween(int fromYear, int toYear);
	
	// JPQL : JPA Query Language
	@Query("select e from EmployeeEntity e where e.birthYear between :fromYear and :toYear")
	// native query :
	//@Query(value="select e from employees e where e.birth_year between :fromYear and :toYear", nativeQuery= true)
	List<EmployeeDocument> bulGetir(int fromYear, int toYear);
	List<EmployeeDocument> findAllByDepartment(String department);
}
