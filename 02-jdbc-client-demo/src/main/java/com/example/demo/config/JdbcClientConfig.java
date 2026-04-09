package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class JdbcClientConfig {
	@Bean
	public JdbcClient jdbcClient(DataSource datasource) {
		return JdbcClient.create(datasource);
	}
}
