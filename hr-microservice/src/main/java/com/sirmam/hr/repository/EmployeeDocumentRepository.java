package com.sirmam.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sirmam.hr.document.EmployeeDocument;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String> {
	List<EmployeeDocument> findAllByBirthYearBetween(int fromYear, int toYear);
	
	@Query("{'birthYear': { $gt : ?0, $lt ?1 } }")
	List<EmployeeDocument> bulGetir(int fromYear, int toYear);
	List<EmployeeDocument> findAllByDepartment(String department);
}
