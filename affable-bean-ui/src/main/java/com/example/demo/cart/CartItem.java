package com.example.demo.cart;

import java.io.Serializable;

import lombok.Data;


@Data
public class CartItem implements Serializable{
	private Long id;
	private String name;
	private double price;
	
	private int quantity;
}
