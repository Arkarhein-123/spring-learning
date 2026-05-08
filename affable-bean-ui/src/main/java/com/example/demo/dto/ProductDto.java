package com.example.demo.dto;

import java.time.LocalDateTime;

public record ProductDto(
		Long id,
		String name, 
		double price, 
		String description, 
		LocalDateTime lastUpdate,
		String categoryName
		) {
}

