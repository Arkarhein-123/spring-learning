package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	// localhost:8080/delete-employee?id=2
	@GetMapping("/delete-employee")
	private String deleteEmployee(@RequestParam("id")long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
	
	@GetMapping("/update-employee")
	public String updateEmployee(@RequestParam("id")long id, Model model) {
		Employee employee = employeeService.findEmployeeByID(id);
		model.addAttribute("employee",employee);
		return "employee-form";
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/employees";
	}
	@RequestMapping("/employees")
	public String home(Model model) {
		model.addAttribute("employees",employeeService.findAllEmployee());
		return "home";
	}
	
	/*	
	 * first face: 
	 * 1. form object // command object = as like a waiter
	 * 2. render form page
	 */
	@RequestMapping("/add-employee")
	public String employeeForm(Model model) {
		model.addAttribute("employee",new Employee());
		return "employee-form";
	}
	
	/*
	 * Second Face:
	 * 1. validate & convert 
	 * 2. save
	 * 3. redirect 
	 */
	@PostMapping("/add-employee")
	public String saveEmployee(@Validated Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			return "employee-form";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
}
