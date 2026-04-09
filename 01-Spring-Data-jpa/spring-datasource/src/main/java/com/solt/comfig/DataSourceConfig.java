package com.solt.comfig;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration

public class DataSourceConfig {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.addScript("schema.sql")
				.addScript("data.sql")
				.generateUniqueName(true)
				.setScriptEncoding("UTF-8")
				.build();
	}

}
