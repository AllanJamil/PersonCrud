package com.example.personcrud;

import com.example.personcrud.domain.Person;
import com.example.personcrud.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PersoncrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersoncrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUp(PersonRepository personRepository) {
        return (args -> {
            personRepository.save(new Person("Jake","Fish", 25, "Male", "jakefish@outlook.com"));
            personRepository.save(new Person("Maria","Hopsson", 33, "Female", "maria87@outlook.com"));
            personRepository.save(new Person("Sara","Downy", 19, "Female", "sarad@hotmail.com"));
            personRepository.save(new Person("Josh","Stone", 40, "Male", "j_stone@gmail.com"));
        });
    }

}
