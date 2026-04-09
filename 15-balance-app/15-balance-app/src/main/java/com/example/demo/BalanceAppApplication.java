package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.demo.dao.BalanceDao;
import com.example.demo.service.BalanceService;

@EnableAspectJAutoProxy
@SpringBootApplication
public class BalanceAppApplication implements CommandLineRunner{
	 private final BalanceService balanceService;
	 private final BalanceDao balanceDao;
	 

	    // ✅ constructor injection
	    public BalanceAppApplication(BalanceDao balanceDao,BalanceService balanceService) {
	        this.balanceDao = balanceDao;
	        this.balanceService = balanceService;
	    }
	

	public static void main(String[] args) {
		SpringApplication.run(BalanceAppApplication.class, args);
	}
	


	@Override
	public void run(String... args) throws Exception {
//		System.out.println("John Balance:"+ balanceDao.getBalance(1));
//		System.out.println("Mary Balance:"+ balanceDao.getBalance(2));
		
		balanceDao.deleteAll();
		try {
			balanceService.createAccountWithTransaction();
		}catch(Exception e) {
			System.out.println("error.");
		}
		balanceDao.listAllBalance().forEach(System.out::println);
		
		
		
	}

}
