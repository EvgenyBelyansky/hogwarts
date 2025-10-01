package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.validation.InputValidator;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final Map<Long, Student> studentHashMap;

    private final InputValidator inputValidator;

    private long count = 1;

    public void addStudent(Student student) {
        inputValidator.checkArgumentIsNull(student);
        inputValidator.checkObjectStringFieldIsBlank("name", student.getName(), student);
        inputValidator.checkObjectNumericFieldIsPositive("age", student.getAge(), student);
        inputValidator.checkMapContainsDuplicateObject(studentHashMap, student);

        student.setId(count);
        studentHashMap.put(student.getId(), student);
        count++;
    }

    public Student removeStudentById(long id) {
        inputValidator.checkMapContainsRequestedKey(studentHashMap, id);

        final Student removedStudent = studentHashMap.remove(id);
        return removedStudent;
    }

    public Student findStudentById(long id) {
        inputValidator.checkMapContainsRequestedKey(studentHashMap, id);

        final Student student = studentHashMap.get(id);
        final Student copyStudent = new Student();
        copyStudent.setId(student.getId());
        copyStudent.setName(student.getName());
        copyStudent.setAge(student.getAge());
        return copyStudent;
    }

    public Student updateStudent(Student student) {
        inputValidator.checkArgumentIsNull(student);
        inputValidator.checkMapContainsRequestedKey(studentHashMap, student.getId());
        inputValidator.checkObjectStringFieldIsBlank("name", student.getName(), student);
        inputValidator.checkObjectNumericFieldIsPositive("age", student.getAge(), student);

        final Student updatedStudent = studentHashMap.get(student.getId());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setName(student.getName());

        final Student copyUpdatedStudent = new Student();
        copyUpdatedStudent.setId(student.getId());
        copyUpdatedStudent.setName(student.getName());
        copyUpdatedStudent.setAge(student.getAge());
        return copyUpdatedStudent;
    }

    public Map<Long, Student> getFiltredByAgeStudentMap(int age) {
        inputValidator.checkObjectNumericFieldIsPositive("age", age);

        final Map<Long, Student> studentMap = studentHashMap.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toMap(Student::getId, s -> s));
        return studentMap;
    }

    public Map<Long, Student> getFiltredByNameStudentMap(String name) {
        inputValidator.checkObjectStringFieldIsBlank("name", name);

        final Map<Long, Student> studentMap = studentHashMap.values().stream()
                .filter(s -> s.getName().equals(name))
                .collect(Collectors.toMap(Student::getId, s -> s));
        return studentMap;
    }

    public Map<Long, Student> getAllStudents() {
        return Collections.unmodifiableMap(studentHashMap);
    }
}