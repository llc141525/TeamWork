package org.example.teamwork.service;

import lombok.RequiredArgsConstructor;
import org.example.teamwork.DTO.ArticleDetailDto;
import org.example.teamwork.Model.Article;
import org.example.teamwork.Util.response.ApiResponse;
import org.example.teamwork.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ApiResponse<?> setArticle(@RequestBody ArticleDetailDto articleDetailDto, Article article) {
        article.setTitle(articleDetailDto.getTitle());
        article.setContent(articleDetailDto.getContent());
        article.setCategory(articleDetailDto.getCategory());
        article.setWatchNum(articleDetailDto.getWatchNum());
        article.setUser(articleDetailDto.getUser());
        articleRepository.save(article);
        return ApiResponse.success("success");
    }

}
