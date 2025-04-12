package com.demo.testApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testApp.req_res_dto.DepartmentRequest;
import com.demo.testApp.req_res_dto.DepartmentResponse;
import com.demo.testApp.services.DepartmentService;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentResponse> upsertDepartment(@RequestBody DepartmentRequest departmentRequest) {

		DepartmentResponse departmentResponse = departmentService.saveAndUpdate(departmentRequest);

		return ResponseEntity.ok(departmentResponse);

	}

}
