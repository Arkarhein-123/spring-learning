package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.web.service.registry.ImportHttpServices;

import com.example.demo.client.AddressClient;

@SpringBootApplication
@EnableResilientMethods
@ImportHttpServices(AddressClient.class)
public class EmployUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployUiApplication.class, args);
	}
}
