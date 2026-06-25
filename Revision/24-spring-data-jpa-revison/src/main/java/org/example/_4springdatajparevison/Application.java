package org.example._4springdatajparevison;

import com.github.javafaker.Faker;
import org.example._4springdatajparevison.entity.Author;
import org.example._4springdatajparevison.entity.Text;
import org.example._4springdatajparevison.entity.Video;
import org.example._4springdatajparevison.entity2.Teacher;
import org.example._4springdatajparevison.repository.AuthorRepository;
import org.example._4springdatajparevison.repository.TeacherRepository;
import org.example._4springdatajparevison.repository.TextRepository;
import org.example._4springdatajparevison.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(TeacherRepository teacherRepository,
                                               TextRepository textRepository,
                                               VideoRepository videoRepository,
                                               AuthorRepository authorRepository){
        return args ->{

             List<Author> authors =  authorRepository.findByFirstName("Arkar");
            System.out.println("*********************Authors*******************");
            System.out.println(authors.toString());

//            authorRepository.updateFirstName(1L,"Arkar");
//            authorRepository.updateLastName(2L, "Hein");

//            for (int i = 0; i < 50; i++) {
//                Faker faker = new Faker();
//                var author = Author.builder()
//                        .firstName(faker.name().firstName())
//                        .lastName(faker.name().lastName())
//                        .email(faker.internet().emailAddress())
//                        .build();
//                authorRepository.save(author);
//            }

//            var teacher = Teacher.builder()
//                    .name("Zayar Htun")
//                    .age(22)
//                    .salary(300000)
//                    .build();
//          teacherRepository.save(teacher);

//            var text = Text.builder()
//                    .name("Text 1")
//                    .content("This is testing one...")
//                    .build();
//            var video = Video.builder()
//                    .name("Video 1")
//                    .length(12)
//                    .build();
//
//            textRepository.save(text);
//            videoRepository.save(video);
        };
    }
}
