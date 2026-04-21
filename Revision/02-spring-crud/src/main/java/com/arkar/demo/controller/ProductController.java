package com.arkar.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.arkar.demo.model.Product;
import com.arkar.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping("/products")
	public String getAllProducts(Model model) {
		var products = productService.findAll();
		model.addAttribute("products",products);
		return "layout/products";
	}
	
	@GetMapping("/add-employee")
	public String employeeForm(Model model) {
		model.addAttribute("product", new Product());
		return "employee-from";
	}
}
