package org.example.teamwork.dto.request;

import lombok.Builder;

@Builder
public record CreateCommentRequest(
        String content,
        Long stars
) {
}
