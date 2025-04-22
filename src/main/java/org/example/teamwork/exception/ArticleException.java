package org.example.teamwork.exception;

public class ArticleException extends BaseException {
    public ArticleException(Integer code, String message) {
        super(code, message, "ArticleError");
    }
}
