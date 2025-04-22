package org.example.teamwork.exception.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ArticleError {
    ;
    private final Integer code;
    private final String message;
}
