package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.service.StudentService;

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

    @GetMapping("/by-age")
    public Map<Long, Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getFiltredByAgeStudentMap(age);
    }

    @GetMapping("/by-name")
    public Map<Long, Student> getStudentsByName(@RequestParam String name) {
        return studentService.getFiltredByNameStudentMap(name);
    }

    @GetMapping("/get/all")
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}