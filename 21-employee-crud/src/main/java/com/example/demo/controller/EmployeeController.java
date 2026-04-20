package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/employees";
	}
	@RequestMapping("/employees")
	public String home(Model model) {
		model.addAttribute("employees",employeeService.findAllEmployee());
		return "home";
	}
	@RequestMapping("/add-employee")
	public String employeeForm(Model model) {
		model.addAttribute("employee",new Employee());
		return "employee-form";
	}
	
	@PostMapping("/add-employee")
	public String saveEmployee(Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			return "employee-form";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
}
