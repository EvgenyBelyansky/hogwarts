package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class DuplicateObjectException extends HogwartsException {
    public DuplicateObjectException(Object object) {
        super(HorwartsErrorCode.OBJECT_IS_DUPLICATE,
                "Переданный объект [%s] уже есть в хранилище!".formatted(object),
                HttpStatus.BAD_REQUEST
        );
    }
}
