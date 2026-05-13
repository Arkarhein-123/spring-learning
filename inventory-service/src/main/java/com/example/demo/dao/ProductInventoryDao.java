package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductInventory;

public interface ProductInventoryDao extends JpaRepository<ProductInventory, Long> {
	Optional<ProductInventory> findByProductId(Long productId);
}
