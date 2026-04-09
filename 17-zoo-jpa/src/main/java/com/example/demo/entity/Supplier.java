package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Supplier extends IdClass{
	private String supplierName;
	private String contactNum;
	private String address;
	@OneToMany(mappedBy = "supplier")
	private List<FoodItem> foodItems = new ArrayList<>();
	
	public Supplier(String supplierName, String contactNum, String address) {
		super();
		this.supplierName = supplierName;
		this.contactNum = contactNum;
		this.address = address;
	}
	

	public void addFoodItem(FoodItem foodItem) {
		foodItem.setSupplier(this);
		foodItems.add(foodItem);
	}
	
	
	
}
