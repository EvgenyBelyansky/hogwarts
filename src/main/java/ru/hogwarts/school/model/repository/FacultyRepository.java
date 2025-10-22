package ru.hogwarts.school.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.entity.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByNameAndColor(String name, String color);

    boolean existsByNameAndColorAndIdNot(String name, String color, Long id);

    List<Faculty> findFacultiesByColorContainingIgnoreCase(String color);

    Collection<? extends Student> getFacultyById(long id);
}
