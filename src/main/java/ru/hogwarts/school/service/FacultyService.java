package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class FacultyService {

    private final HashMap<Long, Faculty> facultyHashMap;

    private int count;

}
