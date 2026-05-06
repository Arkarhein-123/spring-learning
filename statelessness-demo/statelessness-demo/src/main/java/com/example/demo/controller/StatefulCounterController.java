package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.StatefulSessionBean;
import com.example.demo.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/counter/stateful")
public class StatefulCounterController {
	private final StatefulSessionBean statefulSessionBean;
	// http://localhost:8080/api/counter/stateful/countUp
	@PostMapping("/countUp")
	public ResponseDto countUp() {
		statefulSessionBean.increase();
		return new ResponseDto(statefulSessionBean.getCounter());
	}

}






