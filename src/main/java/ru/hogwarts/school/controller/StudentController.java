package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public void addStudent(@RequestBody StudentDto student) {
        studentService.addStudent(student);
    }

    @DeleteMapping("/remove/{id}")
    public void removeStudentById(@PathVariable long id) {
        studentService.removeStudentById(id);
    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudentById(@RequestParam long id) {
        final Student studentById = studentService.findStudentById(id);
        return ResponseEntity.ok(studentById);
    }

    @PatchMapping("/update")
    public StudentDto updateStudent(@RequestParam long id,
                                    @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }

    @GetMapping("/get/all")
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/filter")
    public List<Student> findStudentByFilter(@RequestParam(required = false) int age,
                                             @RequestParam(required = false) int maxAge,
                                             @RequestParam(required = false) String name) {

        return studentService.findStudentByFilter(age, maxAge, name);
    }

    @GetMapping("/faculty")
    public Faculty getStudentFacultyById(@RequestParam long id) {
        return studentService.getStudentFacultyById(id);
    }
}