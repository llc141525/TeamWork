package org.example.teamwork.exception.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserError {
    INVALID_USER(10001, "非法用户名"),
    INVALID_PASSWD_LENGTH(10002, "密码长度至少为8位"),
    DUPLICATE_USER(10002, "重复的用户名"),
    INVALID_USER_PASSWORD(10003, "用户名或者密码错误");
    private final Integer code;
    private final String message;
}
