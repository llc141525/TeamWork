package org.example.teamwork.exception.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ArticleError {
    INVALID_TIME(20001, "非法时间"),
    NO_ARTICLE(20002, "不存在的文章"),
    OTHER_ARTICLE(20003, "别人的文章"),

    ;
    private final Integer code;
    private final String message;
}
