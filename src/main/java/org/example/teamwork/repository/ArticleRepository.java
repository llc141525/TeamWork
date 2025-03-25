package org.example.teamwork.repository;

import org.example.teamwork.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> getArticleById(Long id);

    Article findArticleById(Long id);
}
