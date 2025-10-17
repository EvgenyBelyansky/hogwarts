package ru.hogwarts.school;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.repository.FacultyRepository;

@Component
public class FacultyHelper extends TestHelper<Faculty> {

    @Autowired
    public FacultyHelper(FacultyRepository facultyRepository) {
        super(facultyRepository::save);
    }

    public static Faculty.FacultyBuilder createBuilder() {
        return Faculty.builder()
                .color(faker.color().name())
                .name(faker.harryPotter().house());
    }

    @Override
    public Faculty create() {
        return createBuilder().build();
    }

//    public JpaRepository<Faculty, ?> getRepository() {
//        return facultyRepository;
//    }
}
