package com.arkar.demo;

import com.arkar.demo.dao.EmployeeDao;
import com.arkar.demo.entity.Employee;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    @Bean
    public ApplicationRunner runner(EmployeeDao employeeDao){
        return  r ->{
            List.of(
                    new Employee("Arkar", "Hein", "hein90510@gmail.com", 2000),
                    new Employee("Justin", "Hein", "justin@gmail.com", 2000)
            ).forEach(employeeDao::save);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
