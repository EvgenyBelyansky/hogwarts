package ru.hogwarts.school.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HogwartsExceptionHandler {

    @ExceptionHandler(HogwartsException.class)
    public ResponseEntity<HogwartsError> handleExamException(HogwartsException e) {
        HogwartsError error = new HogwartsError(
                e.getCode(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(error);
    }
}