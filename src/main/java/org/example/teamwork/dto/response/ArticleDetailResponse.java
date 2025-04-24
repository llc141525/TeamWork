package org.example.teamwork.dto.response;

import lombok.Builder;

@Builder
public record ArticleDetailResponse(
        Long id,
        String title,
        String content,
        String category,
        Integer watchNum,
        String username,
        Long createTime
) {
}
