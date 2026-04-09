package com.solt;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.solt.bean.SpringBean1;

@Configuration
@ComponentScan
public class AppConfig {
	
	@Bean(name = {"ArkarBean","YeHtetBean","AungPaeSi"},
			initMethod = "customInit",
			destroyMethod = "customDestory"
			)
	public SpringBean1 springBean1() {
		 return new SpringBean1();
	}
	
	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new CustomBeanFactoryPostProcessor();
	}
	
	@Bean
	public static BeanPostProcessor beanPostProcessor() {
		return new CustomBeanPostProcessor();
	}
}
