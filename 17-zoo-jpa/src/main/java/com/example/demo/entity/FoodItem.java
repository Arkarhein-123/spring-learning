package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodItem extends IdClass {
	
	private String name;
	private int quantity;
	@ManyToMany
	private List<Animal> animals = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Supplier supplier;
	
	public FoodItem(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	
	
}
