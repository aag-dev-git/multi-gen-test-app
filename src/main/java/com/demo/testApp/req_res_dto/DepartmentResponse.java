package com.demo.testApp.req_res_dto;

import java.util.List;

import com.demo.testApp.entities.Employee;

public class DepartmentResponse {

	private String name;

	private String location;

	private List<Employee> employeeList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "DepartmentResponse [name=" + name + ", location=" + location + ", employeeList=" + employeeList + "]";
	}

}
