package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ui/employees")
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@GetMapping
	public String listEmployees(Model model) {
		model.addAttribute("employees",employeeService.listAllEmployees());
		return "employees";
	}
}
