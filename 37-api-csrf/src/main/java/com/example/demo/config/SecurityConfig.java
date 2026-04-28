package com.example.demo.config;

import com.sun.net.httpserver.HttpsExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(c -> {
            c.anyRequest().permitAll();

        });
        return http.build();
    }
}
