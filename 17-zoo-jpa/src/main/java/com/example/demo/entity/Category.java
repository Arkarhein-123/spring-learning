package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Category extends IdClass {
	private String categoryType;
	@OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST)
	private List<Animal> animals = new ArrayList<>();
	
	public void addAnimal(Animal animal) {
		animal.setCategory(this);
		animals.add(animal);
	}
}