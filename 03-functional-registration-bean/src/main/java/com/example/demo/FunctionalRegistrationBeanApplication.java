package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EmployeeBeanRegistrar.class)
public class FunctionalRegistrationBeanApplication implements CommandLineRunner{

	@Autowired
	private Employee employee;

	public static void main(String[] args) {
		SpringApplication.run(FunctionalRegistrationBeanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			System.out.println("Name: %s %s"
					.formatted(employee.getFirstName(), employee.getLastName()));
	}
}
