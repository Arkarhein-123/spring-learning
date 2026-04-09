package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Animal extends IdClass {
	
	private String type;
	private int totalNo;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private  List<FoodItem> foodItems = new ArrayList<>();
	
	@OneToOne(mappedBy = "animal",
			cascade = CascadeType.PERSIST)
	private Cage cage;
	@ManyToOne
	private Category category;
	
	public void addCage(Cage cage) {
		cage.setAnimal(this);
		this.cage = cage;
	}
	
	public void addFoodItem(FoodItem foodItem) {
	foodItem.getAnimals().add(this);
	foodItems.add(foodItem);
	}
		
	public Animal(String type, int totalNo) {
		super();
		this.type = type;
		this.totalNo = totalNo;
	}	
}
