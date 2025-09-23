package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final HashMap<Long, Student> studentHashMap;
}
