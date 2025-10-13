package ru.hogwarts.school.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByNameAndAge(String name, int age);

    boolean existsByNameAndAgeAndIdNot(String name, int age, Long id);
}
