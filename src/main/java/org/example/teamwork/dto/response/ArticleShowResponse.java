package org.example.teamwork.dto.response;

import java.util.List;

public record ArticleShowResponse(
        String title,
        String content,
        String category,
        Integer watchNum,
        List<Long> comments
) {
}
