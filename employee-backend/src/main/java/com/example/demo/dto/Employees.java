package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employees {
	private List<EmployeeDto> employees;

	public Employees(List<EmployeeDto> employees) {
		super();
		this.employees = employees;
	}
	
	
}
