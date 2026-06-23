package org.example._4springdatajparevison;

import org.example._4springdatajparevison.entity.Author;
import org.example._4springdatajparevison.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AuthorRepository authorRepository){
        return args ->{
            var author = Author.builder()
                    .firstName("Orion")
                    .lastName("Justin")
                    .email("orion@gmail.com")
                    .build();
            authorRepository.save(author);
        };
    }
}
