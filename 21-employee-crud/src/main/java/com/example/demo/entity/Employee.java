package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.validation.NamedNotAdmin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NamedNotAdmin(message = "First Name Cannot be Admin!")
	@NotEmpty(message = "First Name Cannot be Empty!")
	@NotBlank(message = "First Name cannot be blank")
	@Pattern(regexp = "^[A-Za-z' ]+$", message = "First Name can't contain illegal characters!")
	private String firstName;
	@NamedNotAdmin(message = "Last Name cannot be Admin!")
	@NotEmpty(message = "Last Name Cannot be Empty!")
	@NotBlank(message = "Last Name cannot be blank")
	@Pattern(regexp = "^[A-Za-z' ]+$", message = "Last Name can't contain illegal characters!")
	private String lastName;
	@Email(message = "Please enter a valid email address")
	private String email;
	private String phoneNumber;
	@Past(message = "Hired Date must be past!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;
	@DecimalMin(value = "1000",message = "Salary must be at least 1000!")
	@DecimalMax(value = "6000",message = "Salary must be at most 6000")
	private double salary;
}
