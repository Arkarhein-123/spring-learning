package com.arkar.springsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserMgmtConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        var user = User.withUsername("Arkar").password("12345").authorities("read").build();
        var user2 = User.withUsername("John").password("12345").authorities("write").build();
        return new InMemoryUserDetailsManager(user,user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
