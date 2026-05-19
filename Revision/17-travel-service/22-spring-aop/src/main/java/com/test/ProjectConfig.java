package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.test")
@EnableAspectJAutoProxy
public class ProjectConfig {
    @Bean
    public LoggingAspect aspect(){
        return new LoggingAspect();
    }
}
