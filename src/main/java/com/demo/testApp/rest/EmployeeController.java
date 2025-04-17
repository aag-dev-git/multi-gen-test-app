package com.demo.testApp.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testApp.enums.Designation;
import com.demo.testApp.req_res_dto.EmployeeRequest;
import com.demo.testApp.req_res_dto.EmployeeResponse;
import com.demo.testApp.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello from Spring Boot....");
	}

	@PostMapping
	public ResponseEntity<EmployeeResponse> upsertEmployee(@RequestBody EmployeeRequest employeeRequest) {

		EmployeeResponse employeeResponse = employeeService.saveAndUpdateEmployee(employeeRequest);

		return ResponseEntity.ok(employeeResponse);

	}

//	@GetMapping
//	public ResponseEntity<List<EmployeeResponse>> getEmployeeBasedOnParam(
//			@RequestParam(required = false) Designation designation, @RequestParam(required = false) Double minSalary,
//			@RequestParam(required = false) Double maxSalary, @RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size) {
//
//		List<EmployeeResponse> response = employeeService.getEmployeesBasedOnParams(designation, minSalary, maxSalary,
//				page, size);
//
//		return ResponseEntity.ok(response);
//	}

	@GetMapping("/{department}")
	public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(@PathVariable String department) {

		List<EmployeeResponse> employeesByDepartmentList = employeeService.getEmployeesByDepartment(department);

		return ResponseEntity.ok(employeesByDepartmentList);

	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestParam(required = false) String name,
			@RequestParam(required = false) Designation designation,
			@RequestParam(required = false) Date joiningDateFrom, @RequestParam(required = false) Date joiningDateTo,
			@RequestParam(required = false) Long departmentId, @RequestParam(required = false) Double minSalary,
			@RequestParam(required = false) Double maxSalary) {

		List<EmployeeResponse> response = employeeService.getEmployeesWithDynamicFilter(name, designation,
				joiningDateFrom, joiningDateTo, departmentId, minSalary, maxSalary);
		return ResponseEntity.ok(response);
	}

}
