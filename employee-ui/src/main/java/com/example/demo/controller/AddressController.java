package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AddressService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui/addresses")
public class AddressController {
	private final AddressService addressService;
	
	@GetMapping("/list")
	public String listAddress(Model model) {
		model.addAttribute("employees",List.of());
		model.addAttribute("addresses",addressService.listAllAddresses());
		return "employees";
	}
}
