package ru.hogwarts.school.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByNameAndAge(String name, int age);

    boolean existsByNameAndAgeAndIdNot(String name, int age, Long id);

    List<Student> findStudentsByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    List<Student> findStudentByAgeAndNameContainingIgnoreCase(int age, String name);

    List<Student> findStudentByAgeBetweenAndNameContainsIgnoreCase(
            int minAge, int maxAge, String name);

    List<Student> findStudentsByNameContainsIgnoreCase(String name);
}
