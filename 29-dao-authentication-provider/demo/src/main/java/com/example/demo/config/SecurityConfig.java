package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception{

        //login
        http.formLogin(c -> {
            c.loginPage("/login").permitAll();
            c.defaultSuccessUrl("/home", true);
        });
        //logout
        http.logout(c -> {
            c.logoutUrl("/logout").permitAll();
            c.logoutSuccessUrl("/login");
        });

        http.authenticationProvider(daoAuthenticationProvider());
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/login", "/register", "/error").permitAll();
            c.anyRequest().authenticated();
        });
        return http.build();
    }
}
