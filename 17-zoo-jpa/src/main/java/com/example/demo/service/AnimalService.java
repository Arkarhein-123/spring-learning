package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Animal;
import com.example.demo.entity.Cage;
import com.example.demo.entity.Category;
import com.example.demo.entity.FoodItem;
import com.example.demo.entity.Supplier;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalService {

	private final CategoryDao categoryDao;

	@Transactional
	public void createDb() {
		
		Cage cage1 = new Cage();
		cage1.setCageNo("Cage 1");
		cage1.setLocation("East");
		
		Cage cage2 = new Cage();
		cage2.setCageNo("Cage 2");
		cage2.setLocation("West");
		
		Cage cage3 = new Cage();
		cage3.setCageNo("Cage 3");
		cage3.setLocation("North");
		
		Cage cage4 = new Cage();
		cage4.setCageNo("Cage 4");
		cage4.setLocation("South");
		
		Animal animal1 = new Animal("Tiger",15);
		Animal animal2 = new Animal("Monkey",100);
		Animal animal3 = new Animal("Deer",12);
		Animal animal4 = new Animal("Ye Htet Aung",1);
		
		Category category1 = new Category();
		category1.setCategoryType("Carnibal");
		Category category2 = new Category();
		category2.setCategoryType("Mammals");
		
		Supplier supplier1  = new Supplier("John Doe","12345","yuangon");
		Supplier supplier2 = new Supplier("Akrar Hein","56778","Sagaing");
		
		FoodItem foodItem1 = new FoodItem("Meat",20);
		FoodItem foodItem2 = new FoodItem("Veg",100);
		FoodItem foodItem3 = new FoodItem("Wheat",200);
		
		animal1.addCage(cage1);
		animal2.addCage(cage2);
		animal3.addCage(cage3);
		animal4.addCage(cage4);
		
		
		category1.addAnimal(animal1);
		category2.addAnimal(animal2);
		category2.addAnimal(animal3);
		category1.addAnimal(animal4);
		
		supplier1.addFoodItem(foodItem1);
		supplier1.addFoodItem(foodItem2);
		supplier2.addFoodItem(foodItem3);
		
		
		animal1.addFoodItem(foodItem1);
		animal2.addFoodItem(foodItem2);
		animal3.addFoodItem(foodItem3);
		animal4.addFoodItem(foodItem2);
		
		
		categoryDao.save(category1);
		categoryDao.save(category2);

		}
}
