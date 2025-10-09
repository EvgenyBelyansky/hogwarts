package ru.hogwarts.school.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByNameAndColor(String name, String color);
    boolean existsByNameAndColorAndIdNot(String name, String color, Long id);
}
