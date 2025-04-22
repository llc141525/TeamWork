package org.example.teamwork.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterLoginRequest(
        @Schema(name = "username", example = "test_test")
        @NotBlank(message = "用户名不能为空")
        String username,

        @Schema(name = "password", example = "passwordpass")
        @NotBlank(message = "密码不能为空")
        String password
) {
}
