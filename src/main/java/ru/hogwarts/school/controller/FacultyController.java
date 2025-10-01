package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Map;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping("/add")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        facultyService.addFaculty(faculty);
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Faculty> removeFacultyById(@PathVariable long id) {
        final Faculty faculty = facultyService.removeFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id) {
        final Faculty facultyById = facultyService.findFacultyById(id);
        return ResponseEntity.ok(facultyById);
    }

    @PatchMapping("/update")
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        final Faculty updatedfaculty = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(updatedfaculty);
    }

    @GetMapping("/by-color")
    public ResponseEntity<Map<Long, Faculty>> getFacultyByColor(String color) {
        final Map<Long, Faculty> filtredByColorFacultyMap = facultyService.getFiltredByColorFacultyMap(color);
        return ResponseEntity.ok(filtredByColorFacultyMap);
    }

    @GetMapping("/get/all")
    public Map<Long, Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }
}