package org.example.teamwork.dto.request;

import java.util.List;

public record CreateArticleRequest(
        String title,
        String content,
        String category,
        Integer watchNum,
        Long userId,
        List<Long> commentIds
) {
}
