package com.example.demo;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.enitty.Employee;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	@Bean @Profile("dev")
	public ApplicationRunner runner(EmployeeDao employeeDao){
		return r ->{
			List.of(
					new Employee("Arkar","Hein","hein90510@gmail.com",20000),
					new Employee("Justin","Hein","Just90510@gmail.com",20000)
			).forEach(employeeDao::save);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
