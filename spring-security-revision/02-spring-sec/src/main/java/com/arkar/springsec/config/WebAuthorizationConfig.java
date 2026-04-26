package com.arkar.springsec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> {
            c.anyRequest().authenticated();
        });
        return http.build();

    }

    UserDetails u;
    GrantedAuthority g;
    UserDetailsService ud;
    UserDetailsManager ugm;
    GrantedAuthority g1 = () -> "ROLE_USER";
    GrantedAuthority g2 = new SimpleGrantedAuthority("ROLE_ADMIN");

}
