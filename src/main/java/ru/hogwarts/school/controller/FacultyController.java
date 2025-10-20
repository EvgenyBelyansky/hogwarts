package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.dto.FacultyDto;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Map;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping("/add")
    public void addFaculty(@RequestBody FacultyDto facultyDto) {
        facultyService.addFaculty(facultyDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFacultyById(@PathVariable long id) {
        facultyService.removeFacultyById(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id) {
        final Faculty facultyById = facultyService.findFacultyById(id);
        return ResponseEntity.ok(facultyById);
    }

    @PatchMapping("/update")
    public void updateFaculty(@RequestParam long id,
                              @RequestBody FacultyDto facultyDto) {
        facultyService.updateFaculty(id, facultyDto);
    }

    @GetMapping("/by-color")
    public Map<Long, Faculty> getFacultyByColor(String color) {
        return facultyService.getFiltredByColorFacultyMap(color);
    }

    @GetMapping("/get/all")
    public Map<Long, Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }
}