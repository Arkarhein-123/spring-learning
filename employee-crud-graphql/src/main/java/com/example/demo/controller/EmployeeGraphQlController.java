package com.example.demo.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeInput;
import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeGraphQlController {
	private final EmployeeService employeeService;
	 
	@QueryMapping
	public List<EmployeeDto> getAllEmployees(){
		return employeeService.findAllEmployees();
	}
	
	@QueryMapping
	public EmployeeDto getEmployeeById(@Argument Long id) {
		return employeeService.findEmployeeById(id);
	}
	
	@MutationMapping
	public EmployeeDto createEmployee(@Argument EmployeeInput employee) {
		return employeeService.createEmployee(employee);
	}
	
	@MutationMapping
	public EmployeeDto updateEmployee(@Argument Long id,@Argument EmployeeInput employee) {
		return employeeService.updateEmployee(id,employee);
	}
	
	@MutationMapping
	public Boolean deleteEmployeeById(@Argument Long id) {
		return employeeService.deleteEmployeeById(id);
	}
}
