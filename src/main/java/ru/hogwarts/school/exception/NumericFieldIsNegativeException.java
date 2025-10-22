package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;

public class NumericFieldIsNegativeException extends HogwartsException {

    public NumericFieldIsNegativeException(String fieldName, int fieldValue) {
        super(HorwartsErrorCode.ARGUMENT_IS_NEGATIVE,
                "Поле [%s] не должно иметь отрицательное значение! Введенное значение [%s]"
                        .formatted(fieldName, fieldValue),
                HttpStatus.BAD_REQUEST);
    }
}
