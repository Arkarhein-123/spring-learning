package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployBackendApplication {
	private final EmployeeDao employeeDao;
	  @Bean @Profile("dev")
	  public ApplicationRunner runner() {
	    return r ->{
	      List.of(
	          new Employee("John","Doe","john@gmail.com","55-555-55",
	              LocalDate.of(2026,3,12),2000),
	          new Employee("Mary", "Shelly", "mary@mail.com", "55-555-56",
	              LocalDate.of(2026,3,10),2500)
	          )
	      .forEach(employeeDao::save);
	    };
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(EmployBackendApplication.class, args);
	}

}
