package ru.hogwarts.school.model.dto;

import lombok.*;
import ru.hogwarts.school.validation.InputValidator;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyDto {

    private String name;

    private String color;

    public void setName(String name) {
        InputValidator.checkObjectStringFieldIsBlank("name", name);
        this.name = name;
    }

    public void setColor(String color) {
        InputValidator.checkObjectStringFieldIsBlank("color", color);
        this.color = color;
    }
}
