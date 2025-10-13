package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.RepositoryNotContainsObjectWithIdException;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.mappers.StudentMapper;
import ru.hogwarts.school.model.repository.StudentRepository;
import ru.hogwarts.school.validation.InputValidator;
import ru.hogwarts.school.validation.RepositoryValidator;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RepositoryValidator repositoryValidator;

    public void addStudent(StudentDto studentDto) {
        InputValidator.checkArgumentIsNull(studentDto);
        InputValidator.checkObjectStringFieldIsBlank("name", studentDto.getName(), studentDto);
        InputValidator.checkObjectNumericFieldIsPositive("age", studentDto.getAge(), studentDto);
        repositoryValidator.checkRepositoryContainsDuplicateObject(
                studentRepository,
                Student.class,
                studentDto,
                () -> studentRepository.existsByNameAndAge(studentDto.getName(), studentDto.getAge())
        );

        Student newStudent = StudentMapper.fromDtoToStudentEntity(studentDto);
        studentRepository.save(newStudent);
    }

    public void removeStudentById(long id) {
        studentRepository.deleteById(id);
    }

    public Student findStudentById(long id) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RepositoryNotContainsObjectWithIdException(id));
        return copyStudent(student);
    }

    public StudentDto updateStudent(long id, StudentDto studentDto) {
        InputValidator.checkArgumentIsNull(studentDto);
        repositoryValidator.checkRepositoryContainsRequestedKey(studentRepository, id);
        InputValidator.checkObjectStringFieldIsBlank("name", studentDto.getName(), studentDto);
        InputValidator.checkObjectNumericFieldIsPositive("age", studentDto.getAge(), studentDto);

        final Student updatedStudent = StudentMapper.fromDtoToStudentEntity(studentDto);
        studentRepository.save(updatedStudent);
        return studentDto;
    }

    public Map<Long, Student> getFiltredByAgeStudentMap(int age) {
        InputValidator.checkObjectNumericFieldIsPositive("age", age);

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toUnmodifiableMap(Student::getId, s -> s));
        return studentMap;
    }

    public Map<Long, Student> getFiltredByNameStudentMap(String name) {
        InputValidator.checkObjectStringFieldIsBlank("name", name);

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .filter(s -> s.getName().equals(name))
                .collect(Collectors.toUnmodifiableMap(Student::getId, s -> s));
        return studentMap;
    }

    public Map<Long, Student> getAllStudents() {

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .collect(Collectors.toUnmodifiableMap(Student::getId, s -> s));
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