package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class ArgumentIsNullException extends HogwartsException {
    public ArgumentIsNullException() {
        super(HorwartsErrorCode.ARGUMENT_IS_NULL,
                "Переданный аргумент не может быть [null]!",
                HttpStatus.BAD_REQUEST);
    }
}