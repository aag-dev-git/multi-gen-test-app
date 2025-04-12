package com.demo.testApp.req_res_dto;

public class DepartmentRequest {

	private String name;

	private String location;

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

	@Override
	public String toString() {
		return "DepartmentRequest [name=" + name + ", location=" + location + "]";
	}

}
