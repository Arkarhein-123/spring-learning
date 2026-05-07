package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductDao productDao;
	
}
