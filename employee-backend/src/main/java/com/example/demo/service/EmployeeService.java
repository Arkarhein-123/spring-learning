package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.Employees;
import com.example.demo.util.EmployeeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeDao employeeDao;
	
	public Employees listEmployees() {
		return new Employees(employeeDao
				.findAll()
				.stream()
				.map(EmployeeUtil::toDto)
				.toList()); 
	}
}
