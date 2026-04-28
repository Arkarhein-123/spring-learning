package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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

    private static final String SUPER_ADMIN = "SUPER_ADMIN";

    private static final String CUSTOMERS_ADMIN = "CUSTOMERS_ADMIN";
    private static final String CUSTOMERS_CREATE = "CUSTOMERS_CREATE";
    private static final String CUSTOMERS_READ = "CUSTOMERS_READ";
    private static final String CUSTOMERS_DELETE = "CUSTOMERS_DELETE";
    private static final String CUSTOMERS_PAG_VIEW = "CUSTOMERS_PAG_VIEW";

    private static final String EMPLOYEES_ADMIN = "EMPLOYEES_ADMIN";
    private static final String EMPLOYEES_CREATE = "EMPLOYEES_CREATE";
    private static final String EMPLOYEES_READ = "EMPLOYEES_READ";
    private static final String EMPLOYEES_DELETE = "EMPLOYEES_DELETE";
    private static final String EMPLOYEES_PAG_VIEW = "EMPLOYEES_PAG_VIEW";

    private static final String DEPARTMENTS_ADMIN = "DEPARTMENTS_ADMIN";
    private static final String DEPARTMENTS_CREATE = "DEPARTMENTS_CREATE";
    private static final String DEPARTMENTS_READ = "DEPARTMENTS_READ";
    private static final String DEPARTMENTS_DELETE = "DEPARTMENTS_DELETE";
    private static final String DEPARTMENTS_PAG_VIEW = "DEPARTMENTS_PAG_VIEW";

    @Bean
    public RoleHierarchy roleHierarchy(){
        return RoleHierarchyImpl.fromHierarchy(
                new RoleHierarchyBuilder()
                        .append(SUPER_ADMIN,EMPLOYEES_ADMIN)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_READ)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_CREATE)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_DELETE)
                        .append(EMPLOYEES_ADMIN,EMPLOYEES_PAG_VIEW)

                        .append(SUPER_ADMIN,DEPARTMENTS_ADMIN)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_CREATE)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_READ)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_DELETE)
                        .append(DEPARTMENTS_ADMIN,DEPARTMENTS_PAG_VIEW)

                        .append(SUPER_ADMIN,CUSTOMERS_ADMIN)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_READ)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_CREATE)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_DELETE)
                        .append(CUSTOMERS_ADMIN,CUSTOMERS_PAG_VIEW)
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(c ->{
            c.loginPage("/login").permitAll();
        });
        http.logout(c -> {
            c.logoutUrl("/logout").permitAll();
            c.logoutSuccessUrl("/login");
        });
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/").permitAll();
//          customer access rules
            c.requestMatchers("/list-customers")
                            .hasAnyRole(CUSTOMERS_READ);
            c.requestMatchers("/customer-form","/delete-customer")
                            .hasAnyRole(SUPER_ADMIN);
            // Employee access Rules
            c.requestMatchers("/list-employees","/employee-form","/delete-employee")
                            .hasAnyRole(SUPER_ADMIN,EMPLOYEES_ADMIN);

            // Department Access Rules
            c.requestMatchers("/list-departments","/department-form")
                            .hasAnyRole(DEPARTMENTS_READ);
            c.requestMatchers("/delete-department")
                            .hasRole(SUPER_ADMIN);

            c.anyRequest().authenticated();

        });
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var john = User.withUsername("john")
                .password("john")
                .roles(SUPER_ADMIN)
                .build();
        var emma = User.withUsername("emma")
                .password("emma")
                .roles(EMPLOYEES_ADMIN)
                .build();
        var william = User.withUsername("william")
                .password("william")
                .roles(DEPARTMENTS_READ,DEPARTMENTS_CREATE)
                .build();
        var lucas = User.withUsername("lucas")
                .password("lucas")
                .roles(CUSTOMERS_READ)
                .build();
        var tom = User.withUsername("tom")
                .password("tom")
                .roles()
                .build();
        return new InMemoryUserDetailsManager(john, emma, william, lucas, tom);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
