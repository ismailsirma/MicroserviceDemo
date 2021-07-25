package com.sirmam.hr.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirmam.hr.application.HrApplication;
import com.sirmam.hr.domain.Employee;
import com.sirmam.hr.domain.FiatCurrency;
import com.sirmam.hr.domain.TcKimlikNo;
import com.sirmam.hr.dto.FireEmployeeResponse;
import com.sirmam.hr.dto.HireEmployeeRequest;
import com.sirmam.hr.dto.HireEmployeeResponse;
import com.sirmam.hr.service.HrService;

@Service
public class SimpleHrService implements HrService {

	@Autowired
	private HrApplication hrApplication;
	
	@Override
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var kimlikNo = TcKimlikNo.valueOf(request.getIdentity());
		Employee employee = new Employee.Builder(kimlikNo)
										.fullname(request.getFirstName(), request.getLastName())
										.iban(request.getIban())
										.salary(request.getSalary(), FiatCurrency.TRY)
										.department(request.getDepartment().name())
										.jobType(request.getType().name())
										.photo(request.getPhoto().getBytes())
										.birthYear(request.getBirthYear())
										.build();
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("success");
	}

	@Override
	public FireEmployeeResponse fireEmployee(String identity) {
		var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity));
		if(employee.isEmpty())
			return new FireEmployeeResponse("failed","Cannot find employee to fire");
		else
			return new FireEmployeeResponse("success");
	}

}
