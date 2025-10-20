package ru.hogwarts.school.model.mappers;

import ru.hogwarts.school.model.dto.FacultyDto;
import ru.hogwarts.school.model.entity.Faculty;

public class FacultyMapper {

    public static Faculty fromDtoToFacultyEntity(FacultyDto facultyDto) {
        return Faculty.builder()
                .name(facultyDto.getName())
                .color(facultyDto.getColor())
                .build();
    }
}

