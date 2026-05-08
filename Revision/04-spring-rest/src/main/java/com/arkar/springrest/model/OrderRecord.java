package com.arkar.springrest.model;

public record OrderRecord(
        String customerName,
        String productName,
        double price
) {
}
