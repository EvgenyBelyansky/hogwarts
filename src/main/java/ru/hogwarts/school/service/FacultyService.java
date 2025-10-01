package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.validation.InputValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FacultyService {

    private final HashMap<Long, Faculty> facultyHashMap;

    private InputValidator inputValidator;

    private long count = 1;

    public void addFaculty(Faculty faculty) {
        inputValidator.checkArgumentIsNull(faculty);
        inputValidator.checkObjectStringFieldIsBlank("name", faculty.getName(), faculty);
        inputValidator.checkObjectStringFieldIsBlank("color", faculty.getColor(), faculty);

        faculty.setId(count);
        facultyHashMap.put(faculty.getId(), faculty);
        count++;
    }

    public Faculty removeFacultyById(long id) {
        inputValidator.checkMapContainsRequestedKey(facultyHashMap, id);

        final Faculty removedFaculty = facultyHashMap.remove(id);
        return removedFaculty;
    }

    public Faculty findFacultyById(long id) {
        inputValidator.checkMapContainsRequestedKey(facultyHashMap, id);

        final Faculty faculty = facultyHashMap.get(id);
        return faculty;
    }

    public Faculty updateFaculty(Faculty faculty) {
        inputValidator.checkArgumentIsNull(faculty);
        inputValidator.checkMapContainsRequestedKey(facultyHashMap, faculty.getId());
        inputValidator.checkObjectStringFieldIsBlank("name", faculty.getName(), faculty);
        inputValidator.checkObjectStringFieldIsBlank("color", faculty.getColor(), faculty);

        final Faculty updatedFaculty = facultyHashMap.get(faculty.getId());
        faculty.setColor(faculty.getColor());
        faculty.setName(faculty.getName());
        return updatedFaculty;
    }

    public Map<Long, Faculty> getFiltredByColorFacultyMap(String color) {
        inputValidator.checkObjectStringFieldIsBlank("color", color);

        final Map<Long, Faculty> facultyMap = facultyHashMap.values().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toMap(Faculty::getId, f -> f));
        return facultyMap;
    }

    public Map<Long, Faculty> getAllFaculty() {
        return Collections.unmodifiableMap(facultyHashMap);
    }
}
