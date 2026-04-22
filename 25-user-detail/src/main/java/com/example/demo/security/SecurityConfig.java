package com.example.demo.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	public SecurityFilterChain securityFilterChain(HttpSecurity http)
	throws Exception{
		http.httpBasic(Customizer.withDefaults());
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		var users = List.of(
					new SecurityUser("Arkar", "12345", "read"),
					new SecurityUser("Justin", "12345", "write"),
					new SecurityUser("Thomas", "12345", "execute")
				);
		return new CustomeUserDetailsService(users);
	}
}
