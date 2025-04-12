package com.demo.testApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.testApp.entities.Salary;
import com.demo.testApp.exception.EmployeeException;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

	Optional<Salary> findByAmount(Double amount);

	List<Salary> findByAmountBetween(Double amount, Double amount_2) throws EmployeeException;

}
