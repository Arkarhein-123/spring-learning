package com.solt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.solt.bean.SpringBean4;
import com.solt.bean.SpringBean5;
import com.solt.bean.SpringBean6;

@Configuration
@ComponentScan(lazyInit = true) //effect to component object
@Lazy
public class AppConfig {
	@Bean
	public SpringBean4 springBean4() {
		return new SpringBean4();
	}
	
	@Bean
	public SpringBean5 springBean5() {
		return new SpringBean5();
	}
	
	@Bean
	@Lazy(false)
	public SpringBean6 springBean6() {
		return new SpringBean6();
	}
	
}
