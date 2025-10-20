package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class RepositoryContainsDuplicateObjectException extends HogwartsException {
    public RepositoryContainsDuplicateObjectException(Class clazz, Object object) {
        super(HorwartsErrorCode.OBJECT_IS_DUPLICATE,
                "Переданный %s [%s] уже есть в хранилище!".formatted(clazz.getSimpleName(), object),
                HttpStatus.BAD_REQUEST
        );
    }
}
