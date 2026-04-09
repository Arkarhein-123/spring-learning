package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	private final JdbcClient jdbcClient;
	
	public EmployeeDao(JdbcClient jdbcClient) {
		super();
		this.jdbcClient = jdbcClient;
		
	}
	
	public List<String> listEmployeeEmails(){
		return jdbcClient.sql("""
				select email from employee
				""").query(String.class).list();
	}
	
}
