package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto student) {
        studentService.addStudent(student);
        return null;
    }

    @DeleteMapping("/remove/{id}")
    public Optional<Student> removeStudentById(@PathVariable long id) {
        final Optional<Student> student = studentService.removeStudentById(id);
        return student;
    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudentById(@RequestParam long id) {
        final Student studentById = studentService.findStudentById(id);
        return ResponseEntity.ok(studentById);
    }

    @PatchMapping("/update")
    public StudentDto updateStudent(@RequestBody Student student) {
        final StudentDto updatedStudent = studentService.updateStudent(student);
        return updatedStudent;
    }

    @GetMapping("/by-age")
    public ResponseEntity<Map<Long, Student>> getStudentsByAge(@RequestParam int age) {
        final Map<Long, Student> filteredByAgeStudentMap = studentService.getFiltredByAgeStudentMap(age);
        return ResponseEntity.ok(filteredByAgeStudentMap);
    }

    @GetMapping("/by-name")
    public ResponseEntity<Map<Long, Student>> getStudentsByName(@RequestParam String name) {
        final Map<Long, Student> filteredByNameStudentMap = studentService.getFiltredByNameStudentMap(name);
        return ResponseEntity.ok(filteredByNameStudentMap);
    }

    @GetMapping("/get/all")
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}