package ru.hogwarts.school.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.validation.InputValidator;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private InputValidator inputValidator;

    @InjectMocks
    private StudentService studentService;


    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setStudentService() {

        studentService = new StudentService(new HashMap<>(), new InputValidator());

        student1 = new Student("Garry Porter", 22);
        student2 = new Student("Ron Whiskey", 21);
        student3 = new Student("Germiona Grange", 23);
    }

    @Test
    @DisplayName("Принимает нового студента и добавляет его в мапу")
    void addStudentTest1() {

        Student testStudent = new Student("Cider Diggory", 21);

        studentService.addStudent(testStudent);

        assertThat(studentService.getAllStudents())
                .hasSize(1)
                .containsValue(testStudent);
    }

    @Test
    @DisplayName("Принимает null студента и выбрасывает ошибку")
    void addStudentTest2() {

        Student testStudent = null;

        assertThatThrownBy(() -> studentService.addStudent(testStudent))
                .isInstanceOf(ArgumentIsNullException.class);
    }

    @Test
    @DisplayName("Принимает студента с пустым полем [name] и выбрасывает ошибку")
    void addStudentTest3() {

        Student testStudent = new Student(" ", 22);

        assertThatThrownBy(() -> studentService.addStudent(testStudent))
                .isInstanceOf(StringIsBlankException.class)
                .hasMessageContaining(testStudent.toString());
    }

    @Test
    @DisplayName("Принимает студента с отрицательным значением поля [age] и выбрасывает ошибку")
    void addStudentTest4() {

        Student testStudent = new Student("Cider Diggory", -21);

        assertThatThrownBy(() -> studentService.addStudent(testStudent))
                .isInstanceOf(NumericFieldIsNotPositiveException.class)
                .hasMessageContaining(testStudent.toString());
    }

    @Test
    @DisplayName("Принимает студента с нулевым значением поля [age] и выбрасывает ошибку")
    void addStudentTest5() {

        Student testStudent = new Student("Cider Diggory", 0);

        assertThatThrownBy(() -> studentService.addStudent(testStudent))
                .isInstanceOf(NumericFieldIsNotPositiveException.class)
                .hasMessageContaining(testStudent.toString());
    }

    @Test
    @DisplayName("Принимает студента дубль и выкидывает ошибку")
    void addStudentTest6() {

        addStudents();
        Student testStudent = new Student("Ron Whiskey", 21);

        assertThat(testStudent).isNotSameAs(student2)
                .isEqualTo(student2);
        assertThatThrownBy(() -> studentService.addStudent(testStudent))
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
    @DisplayName("Принимает несуществующий id и выбрасывает ошибку")
    void removeStudentByIdTest2() {

        addStudents();
        long testId = 5;

        assertThatThrownBy(() -> studentService.removeStudentById(testId))
                .isInstanceOf(RepositoryNotContainsObjectWithIdException.class);
    }

    @Test
    @DisplayName("Принимает существующий id и успешно возвращает копию студента с принятым id")
    void findStudentByIdTest1() {

        addStudents();
        long testId = 2;
        Student returnedStudent = studentService.findStudentById(testId);

        assertThat(returnedStudent).isNotSameAs(student2);
        assertThat(returnedStudent).isEqualTo(student2);
    }

    @Test
    @DisplayName("Принимает несуществующий id и выбрасывает ошибку")
    void findStudentByIdTest2() {

        addStudents();
        long testId = 5;

        assertThatThrownBy(() -> studentService.findStudentById(testId))
                .isInstanceOf(RepositoryNotContainsObjectWithIdException.class);
    }

    @Test
    @DisplayName("")
    void updateStudentTest1() {
    }


    private void addStudents() {
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);
    }

}