package org.example.simplerestapi;

import org.example.simplerestapi.doa.EmployeeDao;
import org.example.simplerestapi.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SimpleRestApiApplication {
    @Autowired
    private EmployeeDao employeeDao;
    @Bean @Profile("dev")
    public ApplicationRunner runner(){
        return args ->{
            var employee1=new Employee("John","Doe","john@mail.com",
                    "55-555-55", LocalDate.now(),2000);
            var employee2=new Employee("Mary","Shelly","mary@mail.com",
                    "55-555-55", LocalDate.now(),2500);
            employeeDao.saveAll(List.of(employee1,employee2));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }

}
