package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
//@DiscriminatorValue("Part_Time")
public class PartTimeEmployee extends CompanyEmployee{
	private double hourRate;

	public PartTimeEmployee(String name, int vacationDays, double hourRate) {
		super(name, vacationDays);
		this.hourRate = hourRate;
	}
	
	
}
