package com.example.demo;

import com.example.demo.bean.Server;
import com.example.demo.register.ServerRegistrar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServerRegistrar.class)
public class Application {
	
	@Autowired
	private Server server;
	
	@Bean
	public ApplicationRunner runner() {
		return args -> {
			server.start();
		};
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
