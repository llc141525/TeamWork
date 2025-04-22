package org.example.teamwork.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {
    private final String message;
    private final Integer code;
    private final String errorType;

    public BaseException(Integer code, String message, String errorType) {
        super(message);
        this.code = code;
        this.message = message;
        this.errorType = errorType;
    }
}
