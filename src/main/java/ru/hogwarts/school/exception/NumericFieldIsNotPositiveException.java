package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class NumericFieldIsNotPositiveException extends HogwartsException {
    public NumericFieldIsNotPositiveException(String fieldName, Object object, int fieldValue) {
        super(HorwartsErrorCode.ARGUMENT_IS_NOT_POSITIVE,
                "Поле [%s] в объекте [%s] должно иметь значение больше 0! Введенное значение [%s]"
                        .formatted(fieldName, object, fieldValue),
                HttpStatus.BAD_REQUEST);
    }

    public NumericFieldIsNotPositiveException(String fieldName, int fieldValue) {
        super(HorwartsErrorCode.ARGUMENT_IS_NOT_POSITIVE,
                "Поле [%s] должно иметь значение больше 0! Введенное значение [%s]"
                        .formatted(fieldName, fieldValue),
                HttpStatus.BAD_REQUEST);
    }
}
