package com.demo.testApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.demo.testApp.entities.Department;
import com.demo.testApp.entities.Employee;
import com.demo.testApp.entities.Salary;
import com.demo.testApp.enums.Designation;
import com.demo.testApp.exception.EmployeeException;

@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

	Optional<Employee> findByNameAndDesignation(String name, Designation designation) throws EmployeeException;

	List<Employee> findByDepartment(Department department) throws EmployeeException;

	List<Employee> findByDesignation(Designation designation) throws EmployeeException;

	List<Employee> findBySalaryIn(List<Salary> salaryList) throws EmployeeException;

}
