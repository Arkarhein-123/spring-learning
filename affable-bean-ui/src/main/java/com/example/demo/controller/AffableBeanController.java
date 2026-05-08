package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.AffableBeanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/affable-bean/ui")
public class AffableBeanController {
	private final AffableBeanService affableBeanService;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	// /affable-bean/ui/products?name=
	@GetMapping("/products")
	public String listProducts(@RequestParam("name")String name,Model model) {
		model.addAttribute("products",affableBeanService.listAllProducts(name));
		return "products";
	}
	
}
