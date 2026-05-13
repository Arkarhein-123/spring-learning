package com.example.demo.controller;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@Autowired
	 private RabbitTemplate rabbitTemplate;
	
	// http://localhost:8080/send/Oid=1
	@GetMapping("/send/{id}")
	public String send(@PathVariable("id")String id) {
		Map<String, String> message = Map.of("orderId",id,"status","CREATED");
		rabbitTemplate.convertAndSend("order_exchange","order.created",message);
		return "Message send: " + id;
	}
}
