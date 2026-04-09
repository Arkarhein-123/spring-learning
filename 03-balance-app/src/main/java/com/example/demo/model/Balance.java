package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Balance {
	private int id;
	private String name;
	private double amount;
	@Override
	public String toString() {
		return "Balance [id=" + id + ", name=" + name + ", amount=" + amount + "]";
	}
	
	
}

