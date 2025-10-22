package ru.hogwarts.school.model.dto;

import lombok.*;
import ru.hogwarts.school.validation.InputValidator;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private String name;
    private int age;
    private long facultyId;

    public void setName(String name) {
        InputValidator.checkObjectStringFieldIsBlank("name", name);
        this.name = name;
    }

    public void setAge(int age) {
        InputValidator.checkObjectNumericFieldIsPositive("age", age);
        this.age = age;
    }



    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        StudentDto that = (StudentDto) object;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}