package com.sirmam.hr.service;

import com.sirmam.hr.dto.FireEmployeeResponse;
import com.sirmam.hr.dto.HireEmployeeRequest;
import com.sirmam.hr.dto.HireEmployeeResponse;

public interface HrService {

	HireEmployeeResponse hireEmployee(HireEmployeeRequest request);

	FireEmployeeResponse fireEmployee(String identity);

}
