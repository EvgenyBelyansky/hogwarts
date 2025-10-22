package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class RepositoryNotContainsObjectWithIdException extends HogwartsException{
    public RepositoryNotContainsObjectWithIdException(long id) {
        super(HorwartsErrorCode.ID_NOT_EXIST,
                "Объекта с Id [%s] не существует в базе!".formatted(id),
                HttpStatus.BAD_REQUEST
        );
    }
}
