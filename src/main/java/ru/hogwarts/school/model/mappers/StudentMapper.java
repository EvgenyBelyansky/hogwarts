package ru.hogwarts.school.model.mappers;

import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Student;

public class StudentMapper {

    public static Student fromDtoToStudentEntity(StudentDto studentDto) {
        return Student.builder()
                .name(studentDto.getName())
                .age(studentDto.getAge())
                .build();
    }
}
