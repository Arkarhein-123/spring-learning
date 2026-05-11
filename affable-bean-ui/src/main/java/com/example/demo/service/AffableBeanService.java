package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.client.ProductClient;
import com.example.demo.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AffableBeanService {
	private final ProductClient productClient;
	
	public List<ProductDto> listAllProducts(String name){
		return productClient.listAllProducts(name);
	}
	
	public ProductDto getProductById(Long id) {
		return productClient.getProductById(id);
	}
	
}
