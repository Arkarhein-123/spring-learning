package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class IndexController {
	
	@GetMapping
	public String home(Model model) {
		model.addAttribute("addresses",List.of());
		model.addAttribute("employees",List.of());
		return "employees";
	}
}
