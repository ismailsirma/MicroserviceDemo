package com.sirmam.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirmam.hr.entity.Employee;

public interface EmployeeSnapshotRepository extends JpaRepository<Employee, String> {

}
