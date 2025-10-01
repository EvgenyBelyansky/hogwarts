package ru.hogwarts.school.validation;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.exception.*;

import java.util.Map;

@Component
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

    public void checkMapContainsRequestedKey(Map map, long id) {
        if (!map.containsKey(id)) {
            throw new MapNotContainsObjectWithIdException(id);
        }
    }

    public void checkMapContainsDuplicateObject(Map map, Object object) {
        if (map.containsValue(object)) {
            throw new DuplicateObjectException(object);
        }
    }
}