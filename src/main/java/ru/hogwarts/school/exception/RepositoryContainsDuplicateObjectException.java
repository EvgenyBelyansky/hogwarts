package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class RepositoryContainsDuplicateObjectException extends HogwartsException {
    public RepositoryContainsDuplicateObjectException(Object object) {
        super(HorwartsErrorCode.OBJECT_IS_DUPLICATE,
                "Переданный объект [%s] уже есть в хранилище!".formatted(object),
                HttpStatus.BAD_REQUEST
        );
    }
}
