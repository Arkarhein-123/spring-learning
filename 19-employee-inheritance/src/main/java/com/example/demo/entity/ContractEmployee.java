package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@DiscriminatorValue("Contract")
public class ContractEmployee extends Employee {
	private int dailyRate;

	public ContractEmployee(String name, int dailyRate) {
		super(name);
		this.dailyRate = dailyRate;
	}
	
	
}
