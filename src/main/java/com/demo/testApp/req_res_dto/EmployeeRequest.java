package com.demo.testApp.req_res_dto;

import java.util.Date;

import com.demo.testApp.enums.Designation;

public class EmployeeRequest {

	private String name;

	private Designation designation;

	private Date joiningDate;

	private String departmentName;

	private Double salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [name=" + name + ", designation=" + designation + ", joiningDate=" + joiningDate
				+ ", departmentName=" + departmentName + ", salary=" + salary + "]";
	}

}
