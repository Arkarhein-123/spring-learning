package org.example._4springdatajparevison;

import org.example._4springdatajparevison.entity.Author;
import org.example._4springdatajparevison.entity.Text;
import org.example._4springdatajparevison.entity.Video;
import org.example._4springdatajparevison.repository.AuthorRepository;
import org.example._4springdatajparevison.repository.TextRepository;
import org.example._4springdatajparevison.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AuthorRepository authorRepository,
                                               TextRepository textRepository,
                                               VideoRepository videoRepository){
        return args ->{
            /*var author = Author.builder()
                    .firstName("Orion")
                    .lastName("Justin")
                    .email("orion@gmail.com")
                    .build();
            authorRepository.save(author);
             */
            var text = Text.builder()
                    .name("Testing 1")
                    .content("This is testing one...")
                    .build();
            textRepository.save(text);
            var video = Video.builder()
                    .name("My Frist Video")
                    .length(12)
                    .build();
            videoRepository.save(video);
        };
    }
}
