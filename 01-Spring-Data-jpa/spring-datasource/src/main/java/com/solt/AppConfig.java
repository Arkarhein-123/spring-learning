package com.solt;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
public class AppConfig {
	@Bean 
	public JdbcTemplate jdbcTemplate(DataSource datasource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(datasource);
		return jdbcTemplate;
	}
}
