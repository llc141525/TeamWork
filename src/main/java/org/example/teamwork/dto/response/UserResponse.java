package org.example.teamwork.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        String username,
        String avatarUrl
) {
}
