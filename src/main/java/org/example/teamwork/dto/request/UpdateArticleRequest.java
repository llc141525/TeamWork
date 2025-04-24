package org.example.teamwork.dto.request;

public record UpdateArticleRequest(
        String title,
        String content,
        String category,
        Integer watchNum

) {
}
