package ru.hogwarts.school.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.exception.*;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class InputValidator {

    public void checkArgumentIsNull(Object object) {
        if (object == null) {
            throw new ArgumentIsNullException();
        }
    }

    public void checkObjectStringFieldIsBlank(String fieldName, String fieldValue, Object object) {
        if (fieldValue.isBlank()) {
            throw new StringIsBlankException(fieldName, object);
        }
    }

    public void checkObjectStringFieldIsBlank(String fieldName, String fieldValue) {
        if (fieldValue.isBlank()) {
            throw new StringIsBlankException(fieldName);
        }
    }

    public void checkObjectNumericFieldIsPositive(String fieldName, int fieldValue, Object object) {
        if (fieldValue <= 0) {
            throw new NumericFieldIsNotPositiveException(fieldName, object, fieldValue);
        }
    }

    public void checkObjectNumericFieldIsPositive(String fieldName, int fieldValue) {
        if (fieldValue <= 0) {
            throw new NumericFieldIsNotPositiveException(fieldName, fieldValue);
        }
    }
}