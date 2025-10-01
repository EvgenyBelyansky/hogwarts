package ru.hogwarts.school.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class HogwartsException extends RuntimeException {

    private final HorwartsErrorCode code;
    private final HttpStatus httpStatus;

    public HogwartsException(HorwartsErrorCode code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public HogwartsException(HorwartsErrorCode code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.I_AM_A_TEAPOT;
    }
}