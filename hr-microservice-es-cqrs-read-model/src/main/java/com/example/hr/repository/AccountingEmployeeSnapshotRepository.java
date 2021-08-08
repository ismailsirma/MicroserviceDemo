package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.AccountingEmployee;

public interface AccountingEmployeeSnapshotRepository extends JpaRepository<AccountingEmployee, String>{

}
