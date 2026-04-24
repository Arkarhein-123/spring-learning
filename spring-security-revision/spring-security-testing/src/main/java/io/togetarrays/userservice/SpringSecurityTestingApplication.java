package io.togetarrays.userservice;

import io.togetarrays.userservice.domain.Role;
import io.togetarrays.userservice.domain.User;
import io.togetarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityTestingApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SALE"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_OFFICE_STAFF"));

            userService.saveUser(new User(null,"Arkar Hein","Arkar","12345",new ArrayList<>()));
            userService.saveUser(new User(null,"Justin Hein","Justin","12345",new ArrayList<>()));
            userService.saveUser(new User(null,"Alien Hein","Alien","12345",new ArrayList<>()));
            userService.saveUser(new User(null,"Orion Hein","Orion","12345",new ArrayList<>()));
            userService.saveUser(new User(null,"Maven Hein","Maven","12345",new ArrayList<>()));

            userService.addRoleToUser("Arkar","ROLE_ADMIN");
            userService.addRoleToUser("Arkar","ROLE_USER");
            userService.addRoleToUser("Justin","ROLE_SALE");
            userService.addRoleToUser("Justin","ROLE_MANAGER");
            userService.addRoleToUser("Orion","ROLE_MANAGER");
            userService.addRoleToUser("Alien","ROLE_OFFICE_STAFF");
        };
    }
}
