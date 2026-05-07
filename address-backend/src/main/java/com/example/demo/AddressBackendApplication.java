package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.example.demo.dao.AddressDao;
import com.example.demo.entity.Address;

@SpringBootApplication
public class AddressBackendApplication {
	@Autowired
	private AddressDao addressDao;
	
	@Bean @Profile("dev")
	public ApplicationRunner runner() {
		return r ->{
			Address address = new Address("Dream Land","Yangon","Myanmar","11041");
			Address address2 = new Address("Padamya Street","Yangon","Myanmar","11041");
			Address address3 = new Address("Kamayut Land","Yangon","Myanmar","43532");
			addressDao.saveAll(List.of(address,address2,address3));
		}; 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AddressBackendApplication.class, args);
	}

}
