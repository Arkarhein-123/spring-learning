package com.example.demo.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterDto;
import com.example.demo.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestBody LoginRequest request
			){
		
		System.out.println(request.username());
		System.out.println(request.password());
		return authService.login(request.username(), request.password());
		
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterDto register) {
		
		return authService.register(register);
	}
	
}
