package ru.hogwarts.school.validation;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.exception.ArgumentIsNullException;
import ru.hogwarts.school.exception.HogwartsException;
import ru.hogwarts.school.exception.NumericFieldIsNotPositiveException;
import ru.hogwarts.school.exception.StringIsBlankException;

import java.util.function.Supplier;

public class InputValidator {

    public static void checkArgumentIsNull(Object object) {
        if (object == null) {
            throw new ArgumentIsNullException();
        }
    }

    public static void checkArgumentIsNull(Object object, Supplier<? extends HogwartsException> supplier) {
        if (object == null) {
            throw supplier.get();
        }
    }


    public static void checkObjectStringFieldIsBlank(String fieldName, String fieldValue, Object object) {
        if (fieldValue == null || fieldValue.isBlank()) {
            throw new StringIsBlankException(fieldName, object);
        }
    }

    public static void checkObjectStringFieldIsBlank(String fieldName, String fieldValue) {
        if (fieldValue.isBlank()) {
            throw new StringIsBlankException(fieldName);
        }
    }

    public static void checkObjectNumericFieldIsPositive(String fieldName, int fieldValue, Object object) {
        if (fieldValue <= 0) {
            throw new NumericFieldIsNotPositiveException(fieldName, object, fieldValue);
        }
    }

    public static void checkObjectNumericFieldIsPositive(String fieldName, int fieldValue) {
        if (fieldValue <= 0) {
            throw new NumericFieldIsNotPositiveException(fieldName, fieldValue);
        }
    }
}