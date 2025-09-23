package ru.hogwarts.school.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Faculty {

    private final Long id;
    private final String name;
    private final String color;

}
