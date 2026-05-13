package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductInventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryApiController {
	private final ProductInventoryService productInventoryService;
	
	record InventoryRequest(
				Long productId,
				int debutQuantity,
				String username
			){}
	
	// http://localhost:8080/api/inventory/debut
	@PostMapping("/debut")
	public ResponseEntity<String> debutQuantity(@RequestBody InventoryRequest request){
		String returnString = productInventoryService
			.debutQuantity(request.productId, request.debutQuantity, request.username);
		return new ResponseEntity(returnString, HttpStatus.OK);
	}
	
}
