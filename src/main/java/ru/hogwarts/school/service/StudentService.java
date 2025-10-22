package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.RepositoryNotContainsObjectWithIdException;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.mappers.StudentMapper;
import ru.hogwarts.school.model.repository.StudentRepository;
import ru.hogwarts.school.validation.InputValidator;
import ru.hogwarts.school.validation.RepositoryValidator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RepositoryValidator repositoryValidator;
    private final StudentMapper studentMapper;

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

        Student newStudent = studentMapper.fromDtoToStudentEntity(studentDto);
        studentRepository.save(newStudent);
    }

    public void removeStudentById(long id) {
        repositoryValidator.checkRepositoryContainsRequestedKey(studentRepository, id);

        studentRepository.deleteById(id);
    }

    public Student findStudentById(long id) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RepositoryNotContainsObjectWithIdException(id));
        return student.clone();
    }

    public StudentDto updateStudent(long id, StudentDto studentDto) {
        InputValidator.checkArgumentIsNull(studentDto);
        repositoryValidator.checkRepositoryContainsRequestedKey(studentRepository, id);
        InputValidator.checkObjectStringFieldIsBlank("name", studentDto.getName(), studentDto);
        InputValidator.checkObjectNumericFieldIsPositive("age", studentDto.getAge(), studentDto);

        final Student updatedStudent = studentMapper.fromDtoToStudentEntity(studentDto);
        studentRepository.save(updatedStudent);
        return studentDto;
    }

    public Map<Long, Student> getAllStudents() {

        final Map<Long, Student> studentMap = studentRepository.findAll()
                .stream()
                .collect(Collectors.toUnmodifiableMap(Student::getId, s -> s));
        return studentMap;
    }

    public List<Student> findStudentByFilter(int age, int maxAge, String name) {
        InputValidator.checkObjectNumericFieldIsNotBeNegative("age", age);
        InputValidator.checkObjectNumericFieldIsNotBeNegative("maxAge", maxAge);

        boolean hasAge = age > 0;
        boolean hasMaxAge = maxAge > 0;
        boolean hasName = name != null && !name.isBlank();

        if (hasAge && hasMaxAge && hasName) {
            return List.copyOf(studentRepository.findStudentByAgeBetweenAndNameContainsIgnoreCase(age, maxAge, name));
        } else if (hasAge && hasMaxAge) {
            return List.copyOf(studentRepository.findByAgeBetween(age, maxAge));
        } else if (hasAge && hasName) {
            return List.copyOf(studentRepository.findStudentByAgeAndNameContainingIgnoreCase(age, name));
        } else if (hasAge) {
            return List.copyOf(studentRepository.findStudentsByAge(age));
        } else if (hasName) {
            return List.copyOf(studentRepository.findStudentsByNameContainsIgnoreCase(name));
        }
        return List.of();
    }

    public Faculty getStudentFacultyById(long id) {
        return findStudentById(id).getFaculty();
    }
}