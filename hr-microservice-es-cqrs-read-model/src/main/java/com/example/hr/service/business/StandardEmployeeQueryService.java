package com.example.hr.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeSnapshotRepository;
import com.example.hr.service.EmployeeQueryService;
import com.example.hr.service.exception.EmployeeNotFoundException;

@Service
public class StandardEmployeeQueryService implements EmployeeQueryService {
	@Autowired
	private EmployeeSnapshotRepository empRepo;
	
	@Value("${server.port}")
	private int port;

	@Override
	public Employee findByIdentity(String identity) {
		return empRepo.findById(identity)
				.orElseThrow(() -> new EmployeeNotFoundException("Cannot find employee to retrieve", "140",
						"d673bc92-9f6f-44d7-8ef6-716ae1b32861", identity));
	}

	@Override
	public List<Employee> findEmployees(int page, int size) {
		System.err.println("Serving at port " + port);
		return empRepo.findAll(PageRequest.of(page, size)).getContent();
	}

}
