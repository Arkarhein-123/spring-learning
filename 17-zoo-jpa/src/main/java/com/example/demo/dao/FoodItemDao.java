package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FoodItem;

public interface FoodItemDao extends JpaRepository<FoodItem, Long> {

}
