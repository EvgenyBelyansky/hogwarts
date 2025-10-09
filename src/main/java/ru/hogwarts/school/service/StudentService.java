package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.StudentRepository;
import ru.hogwarts.school.validation.InputValidator;
import ru.hogwarts.school.validation.RepositoryValidator;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final InputValidator inputValidator;
    private final RepositoryValidator repositoryValidator;

    private long count = 1;

    public void addStudent(StudentDto studentDto) {
        inputValidator.checkArgumentIsNull(studentDto);
        inputValidator.checkObjectStringFieldIsBlank("name", studentDto.getName(), studentDto);
        inputValidator.checkObjectNumericFieldIsPositive("age", studentDto.getAge(), studentDto);
        repositoryValidator.checkRepositoryContainsDuplicateObject(
                studentRepository,
                studentDto,
                () -> studentRepository.existsByNameAndAge(studentDto.getName(), studentDto.getAge())
        );

        Student newStudent = Student.builder()
                .name(studentDto.getName())
                .age(studentDto.getAge())
                .build();

        studentRepository.save(newStudent);
    }

    public Optional<Student> removeStudentById(long id) {
        repositoryValidator.checkRepositoryContainsRequestedKey(studentRepository, id);

        final Optional<Student> removedStudent = studentRepository.findById(id)
                .map(this::copyStudent);
        studentRepository.deleteById(id);
        return removedStudent;
    }

    public Student findStudentById(long id) {
        repositoryValidator.checkRepositoryContainsRequestedKey(studentRepository, id);

        final Student student = studentRepository.findById(id).get();

        return copyStudent(student);
    }

    public StudentDto updateStudent(Student student) {
        inputValidator.checkArgumentIsNull(student);
        repositoryValidator.checkRepositoryContainsRequestedKey(studentRepository, student.getId());
        inputValidator.checkObjectStringFieldIsBlank("name", student.getName(), student);
        inputValidator.checkObjectNumericFieldIsPositive("age", student.getAge(), student);

        final Student updatedStudent = studentRepository.save(student);

        return StudentDto.builder()
                .name(updatedStudent.getName())
                .age(updatedStudent.getAge())
                .build();
    }

    public Map<Long, Student> getFiltredByAgeStudentMap(int age) {
        inputValidator.checkObjectNumericFieldIsPositive("age", age);

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toMap(Student::getId, s -> s));
        return studentMap;
    }

    public Map<Long, Student> getFiltredByNameStudentMap(String name) {
        inputValidator.checkObjectStringFieldIsBlank("name", name);

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .filter(s -> s.getName().equals(name))
                .collect(Collectors.toMap(Student::getId, s -> s));
        return studentMap;
    }

    public Map<Long, Student> getAllStudents() {

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Student::getId, s -> s));
        return studentMap;
    }

    private Student copyStudent(Student student) {
        Student copy = new Student();
        copy.setId(student.getId());
        copy.setName(student.getName());
        copy.setAge(student.getAge());
        return copy;
    }
}