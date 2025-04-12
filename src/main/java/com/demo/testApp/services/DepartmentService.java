package com.demo.testApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.testApp.entities.Department;
import com.demo.testApp.repositories.DepartmentRepository;
import com.demo.testApp.req_res_dto.DepartmentRequest;
import com.demo.testApp.req_res_dto.DepartmentResponse;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	public DepartmentResponse saveAndUpdate(DepartmentRequest departmentRequest) {

		Optional<Department> existDepartmentOpt = departmentRepository
				.findByNameAndLocation(departmentRequest.getName(), departmentRequest.getLocation());

		// Check for existing if not then create new one
		Department department = existDepartmentOpt.orElse(new Department());

		department.setName(departmentRequest.getName());
		department.setLocation(departmentRequest.getLocation());

		DepartmentResponse response = new DepartmentResponse();

		try {
			Department savedDept = departmentRepository.save(department);
			response.setName(savedDept.getName());
			response.setLocation(savedDept.getLocation());

		} catch (Exception e) {
			// ToDo

		}

		return response;

	}

}
