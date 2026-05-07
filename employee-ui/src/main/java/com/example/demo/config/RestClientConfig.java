package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	
	@Value("${address.backend.url}")
	private String addressUri;
	
	@Bean
	public RestClient restClient() {
		return RestClient.builder().baseUrl(addressUri).build();
	}
}
