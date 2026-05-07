package com.example.demo.client;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demo.dto.AddressDto;

@HttpExchange("http://ADDRESS-BACKEND/api/addresses")
public interface AddressClient {
	
	@GetExchange
	List<AddressDto> getAllAddresses();
} 
