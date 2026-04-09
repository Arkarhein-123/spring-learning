package com.example.demo.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class CompanyEmployee extends Employee{
	private int vacationDays;

	public CompanyEmployee(String name, int vacationDays) {
		super(name);
		this.vacationDays = vacationDays;
	}
}
