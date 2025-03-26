package org.example.teamwork.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.example.teamwork.DTO.ArticleDetailDto;
import org.example.teamwork.Model.Article;
import org.example.teamwork.repository.ArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@CommonsLog
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    /*更新一篇文章*/
    public void saveArticle(@RequestBody ArticleDetailDto articleDetailDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDetailDto, article);
        try {
            articleRepository.save(article);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }


}
