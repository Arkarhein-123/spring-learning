package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.EmployeeDao;

@SpringBootApplication
public class JdbcClientDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JdbcClientDemoApplication.class, args);
	}
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("List Employee Emails::");
		employeeDao.listEmployeeEmails().forEach(System.out::println);
		
	}

}
