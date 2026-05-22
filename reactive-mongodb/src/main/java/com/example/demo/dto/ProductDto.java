package com.example.demo.dto;

public record ProductDto(
        String id,
        String name,
        String description,
        double price
) {
}
