package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.example.demo.ds.Employee;

@Repository
public class EmployeeDao {
	
	private final JdbcClient jdbcClient;
	
	public void changeEmailEmployee(int id,String email) {
		jdbcClient.sql("""
				update employee set email =? where id = ?
				""")
		.param(1, email)
		.param(2, id)
		.update();
	}
	
	public EmployeeDao(JdbcClient jdbcClient) {
		super();
		this.jdbcClient = jdbcClient;
	}
	public List<Employee> listEmployees() {
		return jdbcClient.sql("""
				select * from employee
				""")
				.query(Employee.class)
				.list();	
		
	}
	public void createEmployee(Employee employee) {
		jdbcClient.sql("""
				insert into employee(first_name,last_name,email,phone_number,hired_date,salary)
values (?,?,?,?,?,?)
				""")
		.param(1,employee.firstName())
		.param(2,employee.lastName())
		.param(3,employee.email())
		.param(4, employee.phoneNumber())
		.param(5, employee.hiredDate())
		.param(6, employee.salary());
	}
	public void deleteEmployee(int id) {
		jdbcClient.sql("""
				delete from employee where id = ?
				""")
		.param(1,id)
		.update();
	}
	
	public Employee findById(int id) {
		return jdbcClient.sql("""
				select * from employee where id = ?
				""")
				.param(1, id)
				.query(Employee.class)
				.single();
	}
	
	public Employee highSalaryEmployee() {
		return jdbcClient.sql("""
				select * from employee order by salary desc limit 1
				""")
				.query(Employee.class)
				.single();
	}
	public List<String> listEmployeeEmails() {
		return jdbcClient.sql("""
				select email from employee
				""")
				.query(String.class)
				.list();
	} 

}
