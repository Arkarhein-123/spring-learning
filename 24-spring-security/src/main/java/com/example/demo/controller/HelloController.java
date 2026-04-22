package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("/hello")
	@ResponseBody
	public String home(Model model) {	
//		model.addAttribute("msg","Hello Spring Security");
		return "Hello Spring Security"; 
	}
	
	@GetMapping("/home")
	@ResponseBody
	public String home() {
		return "Welcome Home";
	}
}

