package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@SpringBootApplication
public class AffableBeanGatewayApplication {
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    return r ->{
	    	CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(List.of("http://localhost:5173"));
            config.setAllowedMethods(List.of("*"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowCredentials(true);
            config.setExposedHeaders(List.of("*"));
            return config;
	    }; 
	}
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(AffableBeanGatewayApplication.class, args);
	}

}
