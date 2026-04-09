package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Balance;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BalanceDao {
	private final JdbcClient jdbcClient;
	
	public void deleteAll() {
		jdbcClient.sql("""
				delete from balance
				""")
		.update();
	}
	
	public List<Balance> listAllBalance() {
		return jdbcClient.sql("""
				select * from balance
				""")
				.query(Balance.class)
				.list(); 

	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void createAccount(String name, double amount) {
		if(amount < 0) {
			throw new RuntimeException("amount must be valid!");
		}
		jdbcClient.sql("""
				insert into balance(name,amount)
				values(:name,:amount)
				""")
		.param("name", name)
		.param("amount", amount)
		.update();
	}
	
	public void deposit(int id,double amount) {
		double balance = getBalance(id);
		double updatedAmount = balance + amount;
		jdbcClient.sql("""
				update balance set amount =? where id =?
				""")
		.param(1, updatedAmount)
		.param(2, amount)
		.update();
	}
	
	public void withdraw(int id,double amount) {
		double balance = getBalance(id);
		if(amount > balance) {
			System.out.println("Insufficient Amount!!");
			return;
		}
		double updatedAmount = balance - amount;
		jdbcClient.sql("""
				update balance set amount =? where id=?
				""")
		.param(1, updatedAmount)
		.param(2, amount)
		.update();
	}

	
	public double getBalance(int id) {
		return jdbcClient.sql("""
				select amount from balance where id =?
				""")
				.param(1, id)
				.query(Double.class)
				.single();
				
	}
}
