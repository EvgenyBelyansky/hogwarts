package ru.hogwarts.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.entity.Student;

@SpringBootTest
public abstract class BaseTest {

    @Autowired
    protected TestHelper<Student> studentTestHelper;

    @Autowired
    protected TestHelper<Faculty> facultyTestHelper;
}
