package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.AnimalService;

import lombok.RequiredArgsConstructor;


@SpringBootApplication
@RequiredArgsConstructor
public class ZooJpaApplication {
	private final AnimalService animalService;
	
	@Bean
	public ApplicationRunner runner() {
		return r -> {
			animalService.createDb();
			
			JPAUtil.checkData("select * from cage");
			JPAUtil.checkData("select * from animal");
			JPAUtil.checkData("select * from supplier");
			JPAUtil.checkData("select * from category");
			JPAUtil.checkData("select * from food_item");
			JPAUtil.checkData("select * from animal_food_items");
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ZooJpaApplication.class, args);
	}

}
