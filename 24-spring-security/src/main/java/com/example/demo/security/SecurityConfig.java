package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//	@Autowired
//	private AuthenticationProvider authenticationProvider;
	
//	Security System customize
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.httpBasic(Customizer.withDefaults());
//		http.authenticationProvider(authenticationProvider);
		
		var user = User.withUsername("Arkar").password("12345").authorities("read").build();
		var userDetailService = new InMemoryUserDetailsManager();
		
		userDetailService.createUser(user);
		
		http.userDetailsService(userDetailService);
		
		http.authorizeHttpRequests(c -> {
			c.requestMatchers("/home").permitAll(); // permit to home page for all users
			c.anyRequest().authenticated(); // authenticated for other page
		});
		return http.build(); 
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		var user1 = User.withUsername("Arkar").password("12345").authorities("read").build();
//		var user2 = User.withUsername("Justin").password("12345").authorities("write").build();
//		return new InMemoryUserDetailsManager(user1,user2);
//		//InMemoryUserDetailsManagers
//		//JdbcMemoryUserDetailsManager
//	}

}
