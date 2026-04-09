package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BalanceDao;

@Service
public class BalanceService {
	
	private final BalanceDao balanceDao;
	
	public BalanceService(BalanceDao balanceDao) {
		super();
		this.balanceDao = balanceDao;
	}
	
	public void createAccountWithoutTransaction() {
		create();
	}
	@Transactional
	public void createAccountWithTransaction() throws InterruptedException {
		create();
		throw new RuntimeException(); // CheckException
	}

	public void create() {
		balanceDao.createAccount("john", 1000);
		balanceDao.createAccount("mary", 1000);
		balanceDao.createAccount("thomas", 1000);
		balanceDao.createAccount("richard", 1000);
		balanceDao.createAccount("honey", 1000);
	}
	@Transactional
	public void getBalanceWithTransaction() {
		System.out.println("John Balance:"+ balanceDao.getBalance(1));
		System.out.println("John Balance:"+ balanceDao.getBalance(1));
		System.out.println("John Balance:"+ balanceDao.getBalance(1));
		System.out.println("John Balance:"+ balanceDao.getBalance(1));
		System.out.println("John Balance:"+ balanceDao.getBalance(1));
	}
	public void transfer(
			int fromId,
			double fromAmount,
			int toId,
			double toAmount) {
		balanceDao.withdraw(fromId, fromAmount);
		balanceDao.deposit(toId, toAmount);
	}
	

}
