package org.example.teamwork.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.example.teamwork.dto.request.CreateArticleRequest;
import org.example.teamwork.dto.response.ArticleShowResponse;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.repository.CommentRepository;
import org.example.teamwork.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@CommonsLog
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public List<ArticleShowResponse> getArticleShow(Long userId){
        List<ArticleShowResponse> articleShowResponses = new ArrayList<>();
        articleRepository.findAll().forEach(article -> {
            ArticleShowResponse build = ArticleShowResponse.builder()
                    .title(article.getTitle())
                    .content(article.getContent())
                    .category(article.getCategory())
                    .watchNum(article.getWatchNum())
                    .createTime(article.getCreateTime())
                    .userId(article.getUser().getId())
                    .build();
            articleShowResponses.add(build);
        });
        return articleShowResponses;
    }

    public void  saveArticle(CreateArticleRequest createArticleRequest){

    }

}
