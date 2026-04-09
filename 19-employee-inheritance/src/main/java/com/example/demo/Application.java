package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.EmployeeService;

@SpringBootApplication
public class Application {
	@Autowired
	private EmployeeService employeeService;
	@Bean
	public ApplicationRunner runner() {
		return r->{
			employeeService.createDb();
			JPAUtil.checkData("select * from employee");
			JPAUtil.checkData("select * from full_time_employee");
			JPAUtil.checkData("select * from part_time_employee");
			JPAUtil.checkData("select * from contract_employee");
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
