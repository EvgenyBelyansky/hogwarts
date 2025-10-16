package ru.hogwarts.school;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.StudentRepository;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Component
@RequiredArgsConstructor
public class StudentHelper implements TestHelper<Student>{

    private final StudentRepository studentRepository;

    public static Student.StudentBuilder createBuilder() {
        return Student.builder()
                .age(nextInt(1, 100))
                .name(faker.harryPotter().character());
    }

    @Override
    public Student create() {
        return createBuilder().build();
    }

    public JpaRepository<Student, ?> getRepository() {
        return studentRepository;
    }
}
