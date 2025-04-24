package org.example.teamwork.dto.response;

import lombok.Builder;

@Builder
public record GetCommentsResponse(
        String content,
        Long stars,
        Long createTime,
        String username

) {
}
