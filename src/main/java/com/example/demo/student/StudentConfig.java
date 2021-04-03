package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
             Student yousef=new Student(
                    "Yousef",
                    "you@hotmail.com",
                    LocalDate.of(1994, SEPTEMBER,3)
            );
            Student alex=new Student(
                    "Alex",
                    "alex@hotmail.com",
                    LocalDate.of(1990, SEPTEMBER,3)
            );
            repository.saveAll(List.of(yousef,alex));
        };
    }
}
