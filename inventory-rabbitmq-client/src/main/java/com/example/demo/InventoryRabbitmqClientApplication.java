package com.example.demo;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryRabbitmqClientApplication {
	
	@Bean
	public MessageConverter jsonCoverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@RabbitListener(queues = "inventory_queue")
	public void receive(Map<String, String> message) {
		System.out.println("Inventory Consumer Received : "+ message);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InventoryRabbitmqClientApplication.class, args);
		
	}

}
