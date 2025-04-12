package com.demo.testApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.testApp.repositories.SalaryRepository;

@Service
public class SalaryService {

	@Autowired
	SalaryRepository salaryRepository;
}
