package com.demo.testApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.testApp.entities.Department;
import com.demo.testApp.exception.DepartmentException;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<Department> findByNameAndLocation(String name, String location) throws DepartmentException;

	Optional<Department> findByName(String name) throws DepartmentException;
}
