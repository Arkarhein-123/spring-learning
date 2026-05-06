package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	private double salary;
}
