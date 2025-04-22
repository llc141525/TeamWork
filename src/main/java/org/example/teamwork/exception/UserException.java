package org.example.teamwork.exception;

public class UserException extends BaseException {
    public UserException(Integer code, String message) {
        super(code, message, "UserError");
    }
}
