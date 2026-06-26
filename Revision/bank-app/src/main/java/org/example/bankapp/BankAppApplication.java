package org.example.bankapp;

import com.github.javafaker.Faker;
import org.example.bankapp.entity.Account;
import org.example.bankapp.repository.AccountRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

//    @Bean
    public ApplicationRunner runner(AccountRepository accountRepository){
        return args -> {
            for (int i = 0; i <100 ; i++) {
                Faker faker = new Faker();
                String accountHolderName = faker.name().name();
                double balance = faker.number().numberBetween(100,1000);
                Account account = new Account(accountHolderName,balance);
                accountRepository.save(account);
            }
        };
    }

}
