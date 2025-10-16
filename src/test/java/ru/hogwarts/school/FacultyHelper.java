package ru.hogwarts.school;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.FacultyRepository;
import ru.hogwarts.school.model.repository.StudentRepository;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Component
@RequiredArgsConstructor
public class FacultyHelper implements TestHelper<Faculty>{

    private final FacultyRepository facultyRepository;

    public static Faculty.FacultyBuilder createBuilder() {
        return Faculty.builder()
                .color(faker.color().name())
                .name(faker.harryPotter().house());
    }

    @Override
    public Faculty create() {
        return createBuilder().build();
    }

    public JpaRepository<Faculty, ?> getRepository() {
        return facultyRepository;
    }
}
