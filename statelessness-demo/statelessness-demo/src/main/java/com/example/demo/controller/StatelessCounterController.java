package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;

@RestController
@RequestMapping("/api/counter/stateless")
public class StatelessCounterController {
	record RequestDto(int data) {}
	// http://localhost:8080/api/counter/stateless/countUp
	@PostMapping("/countUp")
	public ResponseDto countUp(@RequestBody RequestDto requestDto) {
		return new ResponseDto(requestDto.data + 1);
	}
}



