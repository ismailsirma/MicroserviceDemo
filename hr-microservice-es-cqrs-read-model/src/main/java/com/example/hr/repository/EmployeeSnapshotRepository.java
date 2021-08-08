package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.Employee;

public interface EmployeeSnapshotRepository extends JpaRepository<Employee, String> {

}
