package com.demo.testApp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.demo.testApp.entities.Department;
import com.demo.testApp.entities.Employee;
import com.demo.testApp.entities.Salary;
import com.demo.testApp.enums.Designation;
import com.demo.testApp.exception.EmployeeException;
import com.demo.testApp.repositories.DepartmentRepository;
import com.demo.testApp.repositories.EmployeeRepository;
import com.demo.testApp.repositories.SalaryRepository;
import com.demo.testApp.req_res_dto.EmployeeRequest;
import com.demo.testApp.req_res_dto.EmployeeResponse;
import com.demo.testApp.req_res_dto.EmployeeSpecifications;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	SalaryRepository salaryRepository;

	public EmployeeResponse saveAndUpdateEmployee(EmployeeRequest employeeRequest) {

		Optional<Employee> optionalEmp = employeeRepository.findByNameAndDesignation(employeeRequest.getName(),
				employeeRequest.getDesignation());

		Employee employee = optionalEmp.orElse(new Employee());

		String reqDeptName = employeeRequest.getDepartmentName();

		Department department = departmentRepository.findByName(reqDeptName).get();

		Salary salary = salaryRepository.findByAmount(employeeRequest.getSalary()).get();

		employee.setName(employeeRequest.getName());
		employee.setDesignation(employeeRequest.getDesignation());
		employee.setJoiningDate(employeeRequest.getJoiningDate());
		employee.setDepartment(department);
		employee.setSalary(salary);

		EmployeeResponse employeeResponse = new EmployeeResponse();

		try {

			Employee savedEmp = employeeRepository.save(employee);

			employeeResponse.setName(savedEmp.getName());
			employeeResponse.setDesignation(savedEmp.getDesignation());
			employeeResponse.setJoiningDate(savedEmp.getJoiningDate());
			employeeResponse.setDepartmentName(savedEmp.getDepartment().getName());
			employeeResponse.setSalary(savedEmp.getSalary().getAmount());

		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}

		return employeeResponse;

	}

	public List<EmployeeResponse> getEmployeesByDepartment(String departmentName) {

		List<EmployeeResponse> employeeResponseList = new ArrayList<EmployeeResponse>();

		Department department = departmentRepository.findByName(departmentName).get();

		List<Employee> listOfEmployees = null;
		try {
			listOfEmployees = employeeRepository.findByDepartment(department);

		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}

		listOfEmployees.forEach(employee -> {

			EmployeeResponse employeeResponse = new EmployeeResponse();

			employeeResponse.setName(employee.getName());
			employeeResponse.setDesignation(employee.getDesignation());
			employeeResponse.setJoiningDate(employee.getJoiningDate());
			employeeResponse.setDepartmentName(employee.getDepartment().getName());
			employeeResponse.setSalary(employee.getSalary().getAmount());

			employeeResponseList.add(employeeResponse);
		}

		);

		return employeeResponseList;

	}

	public List<EmployeeResponse> getEmployeesBasedOnParams(Designation designation, Double minSalary, Double maxSalary,
			Integer pageNum, Integer size) {

		List<EmployeeResponse> employeeResponseList = new ArrayList<EmployeeResponse>();

		List<Employee> listOfEmployees = null;
		try {

			if (designation != null) {
				listOfEmployees = employeeRepository.findByDesignation(designation);
			} else if (minSalary != null && maxSalary != null) {

				// First find salaries
				List<Salary> listOfSal = salaryRepository.findByAmountBetween(minSalary, maxSalary);

				listOfEmployees = employeeRepository.findBySalaryIn(listOfSal);
			} else {
				// create Pageable instance
				Pageable pageable = PageRequest.of(pageNum, size, Sort.by("id").ascending());
				Page<Employee> page = employeeRepository.findAll(pageable);
				listOfEmployees = page.getContent();
			}

		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}

		listOfEmployees.forEach(employee -> {

			EmployeeResponse employeeResponse = new EmployeeResponse();

			employeeResponse.setName(employee.getName());
			employeeResponse.setDesignation(employee.getDesignation());
			employeeResponse.setJoiningDate(employee.getJoiningDate());
			employeeResponse.setDepartmentName(employee.getDepartment().getName());

			employeeResponseList.add(employeeResponse);
		}

		);

		return employeeResponseList;

	}

	public List<EmployeeResponse> getEmployeesWithDynamicFilter(String name, Designation designation, Date joiningDateFrom,
			Date joiningDateTo, Long departmentId, Double minSalary, Double maxSalary) {

		Specification<Employee> spec = EmployeeSpecifications.withDynamicFilter(name, designation, joiningDateFrom,
				joiningDateTo, departmentId, minSalary, maxSalary);

		List<Employee> employees = employeeRepository.findAll(spec);

		return employees.stream().map(emp -> {
			EmployeeResponse res = new EmployeeResponse();
			res.setName(emp.getName());
			res.setDesignation(emp.getDesignation());
			res.setJoiningDate(emp.getJoiningDate());
			res.setDepartmentName(emp.getDepartment() != null ? emp.getDepartment().getName() : null);
			return res;
		}).collect(Collectors.toList());
	}

}
