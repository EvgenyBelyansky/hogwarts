package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class MapNotContainsObjectWithIdException extends HogwartsException{
    public MapNotContainsObjectWithIdException(long id) {
        super(HorwartsErrorCode.ID_NOT_EXIST,
                "Объекта с Id [%s] не существует!".formatted(id),
                HttpStatus.BAD_REQUEST
        );
    }
}
