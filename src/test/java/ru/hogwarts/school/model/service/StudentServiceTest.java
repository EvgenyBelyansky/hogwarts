package ru.hogwarts.school.model.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.validation.RepositoryValidator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RepositoryValidator repositoryValidator;

    @Autowired
    private StudentService studentService;


    private final StudentDto studentDto1 = new StudentDto("Garry Porter", 22);
    private final StudentDto studentDto2 = new StudentDto("Ron Whiskey", 21);
    private final StudentDto studentDto3 = new StudentDto("Germiona Grange", 23);


    @Test
    @DisplayName("Принимает дто нового студента, маппером переводит его в энтити и сохратяет в репозиторий")
    void addStudentTest1() {

        StudentDto testStudentDto = studentDto1;

        studentService.addStudent(testStudentDto);

        final List<Student> all = studentRepository.findAll();

        assertThat(all).hasSize(1);
        final Student testStudent = all.get(0);

        assertThat(testStudent.getName()).isEqualTo(testStudentDto.getName());
        assertThat(testStudent.getAge()).isEqualTo(testStudentDto.getAge());
    }

    @Test
    @DisplayName("Принимает null дто студента и выбрасывает ошибку")
    void addStudentTest2() {

        StudentDto testStudentDto = null;

        assertThatThrownBy(() -> studentService.addStudent(testStudentDto))
                .isInstanceOf(ArgumentIsNullException.class);
    }

    @Test
    @DisplayName("Принимает дто студента с пустым полем [name] и выбрасывает ошибку")
    void addStudentTest3() {

        StudentDto testStudentDto = new StudentDto(" ", 22);

        assertThatThrownBy(() -> studentService.addStudent(testStudentDto))
                .isInstanceOf(StringIsBlankException.class)
                .hasMessageContaining(testStudentDto.toString());
    }

    @Test
    @DisplayName("Принимает дто студента с [null] полем [name] и выбрасывает ошибку")
    void addStudentTest4() {

        StudentDto testStudentDto = new StudentDto(null, 22);

        assertThatThrownBy(() -> studentService.addStudent(testStudentDto))
                .isInstanceOf(StringIsBlankException.class)
                .hasMessageContaining(testStudentDto.toString());
    }

    @Test
    @DisplayName("Принимает студента с отрицательным значением поля [age] и выбрасывает ошибку")
    void addStudentTest5() {

        StudentDto testStudentDto = new StudentDto("Cider Diggory", -21);

        assertThatThrownBy(() -> studentService.addStudent(testStudentDto))
                .isInstanceOf(NumericFieldIsNotPositiveException.class)
                .hasMessageContaining(testStudentDto.toString());
    }

    @Test
    @DisplayName("Принимает студента с нулевым значением поля [age] и выбрасывает ошибку")
    void addStudentTest6() {

        StudentDto testStudentDto = new StudentDto("Cider Diggory", 0);

        assertThatThrownBy(() -> studentService.addStudent(testStudentDto))
                .isInstanceOf(NumericFieldIsNotPositiveException.class)
                .hasMessageContaining(testStudentDto.toString());
    }

    @Test
    @DisplayName("Принимает дто студента с уже имеющимся набором полей (дубль) и выкидывает ошибку")
    void addStudentTest7() {

        studentService.addStudent(studentDto2);
        StudentDto testStudentDto = new StudentDto("Ron Whiskey", 21);

        assertThatThrownBy(() -> studentService.addStudent(testStudentDto))
                .isInstanceOf(RepositoryContainsDuplicateObjectException.class);
    }

    @Test
    @DisplayName("Принимает существующий id и успешно удаляет студента с принятым id")
    void removeStudentByIdTest1() {

        addStudents();
        long testId = 2;

        studentService.removeStudentById(testId);

        assertThat(studentService.getAllStudents())
                .hasSize(2)
                .doesNotContainKey(testId);
    }

    @Test
    @DisplayName("Принимает несуществующий в бд id и выбрасывает ошибку")
    void removeStudentByIdTest2() {

        addStudents();
        long testId = 5;

        assertThat(studentService.getAllStudents()).doesNotContainKey(testId);
        assertThatThrownBy(() -> studentService.removeStudentById(testId))
                .isInstanceOf(RepositoryNotContainsObjectWithIdException.class)
                .hasMessageContaining(String.valueOf(testId));
    }
//
    @Test
    @DisplayName("Принимает существующий id и успешно возвращает копию студента с принятым id")
    void findStudentByIdTest1() {

        addStudents();
        long testId = 2;
        Student returnedStudent = studentService.findStudentById(testId);

        assertThat(returnedStudent.getName()).isEqualTo(studentDto2.getName());
        assertThat(returnedStudent.getAge()).isEqualTo(studentDto2.getAge());
    }

    @Test
    @DisplayName("Принимает несуществующий id и выбрасывает ошибку")
    void findStudentByIdTest2() {

        addStudents();
        long testId = 5;

        assertThatThrownBy(() -> studentService.findStudentById(testId))
                .isInstanceOf(RepositoryNotContainsObjectWithIdException.class);
    }
//
//    @Test
//    @DisplayName("")
//    void updateStudentTest1() {
//    }
//
//
    private void addStudents() {
        studentService.addStudent(studentDto1);
        studentService.addStudent(studentDto2);
        studentService.addStudent(studentDto3);
    }


}