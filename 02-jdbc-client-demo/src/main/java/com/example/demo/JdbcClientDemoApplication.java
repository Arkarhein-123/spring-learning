package com.example.demo;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.ds.Employee;

@SpringBootApplication
public class JdbcClientDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JdbcClientDemoApplication.class, args);
	}
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("List employee emails::");
		employeeDao.listEmployeeEmails().forEach(System.out::println);
		System.out.println("List Employees::");
		employeeDao.listEmployees().forEach(System.out::println);
		System.out.println("Employee ById:" + employeeDao.findById(1));
		System.out.println("High Salary Employee:" + employeeDao.highSalaryEmployee());
		employeeDao.createEmployee(new Employee(null, "Thuza","New","nwe@mail.com","22-222-222",Date.valueOf("2024-03-11"),1500));
		System.out.println("List Employees");
		employeeDao.listEmployees().forEach(System.out::println);
		employeeDao.deleteEmployee(2);
		System.out.println("List Employees");
		employeeDao.listEmployees().forEach(System.out::println);
		employeeDao.changeEmailEmployee(1, "johndoe@mail.com");
		System.out.println("List Emplouyees");
		employeeDao.listEmployees().forEach(System.out::println);
	
	}
	

}
