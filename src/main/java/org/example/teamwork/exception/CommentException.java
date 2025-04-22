package org.example.teamwork.exception;

public class CommentException extends BaseException {
    public CommentException(Integer code, String message) {
        super(code, message, "CommentError");
    }
}
