package com.sirmam.hr;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sirmam.hr.entity.Department;
import com.sirmam.hr.entity.Employee;
import com.sirmam.hr.eventsource.EmployeeCreatedEvent;
import com.sirmam.hr.service.EmployeeCommandService;
import com.sirmam.hr.service.EmployeeQueryService;
import com.fasterxml.jackson.databind.ObjectMapper;

// classes: @SpringBootApplication
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = HrMicroserviceApplication.class)
@AutoConfigureMockMvc
class HrMicroserviceConvertToEsCqrsApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmployeeQueryService empQuerySrv;
	@MockBean
	private EmployeeCommandService empCmdSrv;
	@Autowired
	private ObjectMapper mapper;
	
	// Test Doubles: Mocking
	@DisplayName("Sends get employee request by identity and return an employee successfully")
	@Test
	void getEmployeeByIdentity_ThenSuccess() throws Throwable {
		// 1. Test Fixture/Setup
		var identity = "11111111110";
		var fullname = "Jack Bauer";
		var salary = 100_000.0;
		var iban = "TR110006252675695776477213";
		var jack = new Employee();
		jack.setIdentity(identity);
		jack.setFullname(fullname);
		jack.setFulltime(false);
		jack.setSalary(salary);
		jack.setIban(iban);
		jack.setDepartment(Department.IT);
		jack.setBirthYear(1956);
		Mockito.when(empQuerySrv.findByIdentity(identity)).thenReturn(jack);
		// 2. Call exercise method + 3. Validation
		mockMvc.perform(
				   MockMvcRequestBuilders.get("/employees/"+identity)
				                         .accept(MediaType.APPLICATION_JSON_VALUE)
			    ).andExpect(status().isOk())
		         .andExpect(jsonPath("identity", is(identity)))
		         .andExpect(jsonPath("fullname", is(fullname)))
		         .andExpect(jsonPath("iban", is(iban)))
		         .andExpect(jsonPath("salary", is(salary)));
		// 4. tear-down
		// no operation is needed
	}

	
	@DisplayName("Sends a POST request to add new employee and then returns 200")
	@Test
	public void addEmployeeThenReturnsSuccess() throws Throwable {
		// 1. Test Fixture/Setup
		var identity = "11111111110";
		var fullname = "Jack Bauer";
		var salary = 100_000.0;
		var iban = "TR110006252675695776477213";
		var jack = new Employee();
		jack.setIdentity(identity);
		jack.setFullname(fullname);
		jack.setFulltime(false);
		jack.setSalary(salary);
		jack.setIban(iban);
		jack.setDepartment(Department.IT);
		jack.setBirthYear(1956);
		var event = new EmployeeCreatedEvent();
		Mockito.when(empCmdSrv.addEmployee(jack)).thenReturn(event);
		event.setIdentity(identity);
		// 2. Call exercise method + 3. Validation
		mockMvc.perform(
				MockMvcRequestBuilders.post("/employees")
				.content(mapper.writeValueAsString(jack))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(status().isOk())
		.andExpect(jsonPath("identity", is(identity)));
		
		// 4. tear-down
		// no operation is needed		
	}
	
	@DisplayName("Sends a POST request to add new employee and then returns 400")
	@Test
	public void addEmployeeThenReturnsValidationError() throws Throwable {
		// 1. Test Fixture/Setup
		var identity = "11111111111";
		var fullname = "Jack Bauer";
		var salary = 1_000.0;
		var iban = "TR1";
		var jack = new Employee();
		jack.setIdentity(identity);
		jack.setFullname(fullname);
		jack.setFulltime(false);
		jack.setSalary(salary);
		jack.setIban(iban);
		jack.setDepartment(Department.IT);
		jack.setBirthYear(1956);
		var event = new EmployeeCreatedEvent();
		Mockito.when(empCmdSrv.addEmployee(jack)).thenReturn(event);
		event.setIdentity(identity);
		// 2. Call exercise method + 3. Validation
		mockMvc.perform(
				MockMvcRequestBuilders.post("/employees")
				.content(mapper.writeValueAsString(jack))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(status().isBadRequest());
		// 4. tear-down
		// no operation is needed		
	}
}
