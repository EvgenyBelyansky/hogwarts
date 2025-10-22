package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class StringIsBlankException extends HogwartsException {
    public StringIsBlankException(String fieldName, Object object) {
        super(HorwartsErrorCode.STRING_IS_BLANK,
                "Поле [%s] в объекте [%s] не может быть пустым!".formatted(fieldName,object),
                HttpStatus.BAD_REQUEST);
    }

    public StringIsBlankException(String fieldName) {
        super(HorwartsErrorCode.STRING_IS_BLANK,
                "Поле [%s] в запросе не может быть пустым!".formatted(fieldName),
                HttpStatus.BAD_REQUEST);
    }
}