package com.arkar.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arkar.demo.model.Product;

@Service
public class ProductService {
	private List<Product> products = new ArrayList<>();
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public List<Product> findAll(){
		return products;
	}
}
