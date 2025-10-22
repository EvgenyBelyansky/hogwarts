package ru.hogwarts.school.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class HogwartsError {

    private final HorwartsErrorCode code;

    private final String message;
}