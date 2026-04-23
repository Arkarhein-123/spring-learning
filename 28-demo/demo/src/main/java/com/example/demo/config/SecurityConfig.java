package com.example.demo.config;

import com.example.demo.filters.AuthenticationLoggingFilter;
import com.example.demo.filters.RequestValidationFilter;
import com.example.demo.filters.StatickeyValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class SecurityConfig {
    @Autowired
   private StatickeyValidationFilter statickeyValidationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.addFilterAt(statickeyValidationFilter, BasicAuthenticationFilter.class);
//        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
//        http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
//        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
        return http.build();
    }
}
