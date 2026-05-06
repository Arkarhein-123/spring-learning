package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employees;
import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@GetMapping
	public Employees listAllEmployees() {
		return employeeService.listEmployees();
	}
	
}
