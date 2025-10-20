package ru.hogwarts.school.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.hogwarts.school.validation.InputValidator;

import java.util.Objects;

@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Builder
    public Faculty(String name, String color) {
        this.name = Objects.requireNonNull(name);
        this.color = Objects.requireNonNull(color);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Faculty faculty = (Faculty) object;
        return Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    public void setId(long id) {
        this.id = Objects.requireNonNull(id);
    }

    public void setColor(String color) {
        InputValidator.checkObjectStringFieldIsBlank("color", color);
        this.color = color;
    }

    public void setName(String name) {
        InputValidator.checkObjectStringFieldIsBlank("name", name);
        this.name = name;
    }
}