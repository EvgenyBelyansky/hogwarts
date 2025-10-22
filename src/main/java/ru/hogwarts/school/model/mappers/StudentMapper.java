package ru.hogwarts.school.model.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.exception.RepositoryNotContainsObjectWithIdException;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.entity.Student;
import ru.hogwarts.school.model.repository.FacultyRepository;

@Component
@RequiredArgsConstructor
public class StudentMapper{

    private final FacultyRepository facultyRepository;


    public Student fromDtoToStudentEntity(StudentDto studentDto) {

        Faculty faculty = facultyRepository.findById(studentDto.getFacultyId())
                .orElseThrow(() -> new RepositoryNotContainsObjectWithIdException(studentDto.getFacultyId()));

        return Student.builder()
                .name(studentDto.getName())
                .age(studentDto.getAge())
                .faculty(faculty)
                .build();
    }
}