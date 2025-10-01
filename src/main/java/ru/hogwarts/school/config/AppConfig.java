package ru.hogwarts.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;

@Configuration
public class AppConfig {

    @Bean
    public HashMap<Long, Faculty> facultyHashMap() {
        return new HashMap<>();
    }

    @Bean
    public HashMap<Long, Student> studentHashMap() {
        return new HashMap<>();
    }
}