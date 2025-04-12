package com.demo.testApp.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Department extends BaseEntity {

	private String name;

	private String location;

	@OneToMany(mappedBy = "id")
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
		return "Department [name=" + name + ", location=" + location + ", employeeList=" + employeeList + "]";
	}

}
