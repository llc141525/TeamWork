package org.example.teamwork.dto.response;

import lombok.Builder;

@Builder
public record ArticleShowResponse(
        Long id,
        String title,
        String content,
        String category,
        Integer watchNum,
        Long userId,
        Long createTime
) {
}
