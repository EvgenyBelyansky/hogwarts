package ru.hogwarts.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.StudentRepository;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Component
public class StudentHelper extends TestHelper<Student> {


    @Autowired
    public StudentHelper(StudentRepository studentRepository) {
        super(studentRepository::save);
    }

    public static Student.StudentBuilder createBuilder() {
        return Student.builder()
                .age(nextInt(1, 100))
                .name(faker.harryPotter().character());
    }

    @Override
    public Student create() {
        return createBuilder().build();
    }

}
