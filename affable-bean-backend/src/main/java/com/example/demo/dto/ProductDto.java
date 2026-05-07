package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private Long id;
	private String name;
	private double price;
	private String description;
	private LocalDateTime lastUpdate;
}
