package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/home")
                    .permitAll();
            c.requestMatchers("/products/delete")
                    .hasAnyRole("write","admin"); //.hasAnyAuthority("ROLE_admin"."ROLE_write")
            c.requestMatchers("/products/**")
                    .hasRole("admin"); //.hasAuthority(ROLE_admin)

//            c.requestMatchers("/products/delete").hasAnyAuthority("ROLE_admin","ROLE_write");
//            c.requestMatchers("/products/**").hasAuthority("ROLE_admin");
        });
        http.csrf(c ->c.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var user1 = User
                .withUsername("Arkar")
                .password("12345")
//                .authorities("ROLE_read")
                .roles("read")
                .build();
        var user2 = User
                .withUsername("Mary")
                .password("12345")
//                .authorities("ROLE_admin")
                .roles("admin")
                .build();
        var user3 = User
                .withUsername("John")
                .password("12345")
//                .authorities("ROLE_write")
                .roles("write")
                .build();
        return new InMemoryUserDetailsManager(user1,user2,user3);
    }

}