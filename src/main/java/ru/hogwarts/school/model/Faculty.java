package ru.hogwarts.school.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Faculty {

    private long id;
    private String name;
    private String color;

    public Faculty(String name, String color) {
        this.id = 1;
        this.name = name;
        this.color = color;
    }
}