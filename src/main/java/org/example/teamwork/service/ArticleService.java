package org.example.teamwork.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.example.teamwork.dto.request.CreateArticleRequest;
import org.example.teamwork.dto.request.UpdateArticleRequest;
import org.example.teamwork.dto.response.ArticleDetailResponse;
import org.example.teamwork.dto.response.ArticleShowResponse;
import org.example.teamwork.exception.ArticleException;
import org.example.teamwork.exception.errors.ArticleError;
import org.example.teamwork.model.Article;
import org.example.teamwork.model.Comment;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.repository.CommentRepository;
import org.example.teamwork.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
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

    private Article judgeArticle(Long articleId, Long userId) {
        // 先判断 article 是否存在, 然后再看一下他是否被关联的用户操作
        Article article = articleRepository.findById(articleId).orElse(null);
        if (article == null) {
            throw new ArticleException(
                    ArticleError.NO_ARTICLE.getCode(),
                    ArticleError.NO_ARTICLE.getMessage()
            );
        }
        if (!article.getUser().getId().equals(userId)) {
            throw new ArticleException(
                    ArticleError.OTHER_ARTICLE.getCode(),
                    ArticleError.OTHER_ARTICLE.getMessage()
            );
        }
        return article;
    }

    public List<ArticleShowResponse> getArticleShow(Long userId) {
        List<ArticleShowResponse> articleShowResponses = new ArrayList<>();
        articleRepository.findByUserId(userId).forEach(article -> {
            Long timeStamp = article.getCreateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            ArticleShowResponse build = ArticleShowResponse.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .category(article.getCategory())
                    .watchNum(article.getWatchNum())
                    .userId(article.getUser().getId())
                    .createTime(timeStamp)
                    .build();
            articleShowResponses.add(build);
        });
        return articleShowResponses;
    }

    public void saveArticle(CreateArticleRequest createArticleRequest, Long userId) {
        List<Comment> comments = new ArrayList<>();
        createArticleRequest.commentIds().forEach(commentId -> {
            commentRepository.findById(commentId).ifPresent(comments::add);
        });

        userRepository.findById(userId).ifPresent(user -> {
            Article article = Article.builder()
                    .user(user)
                    .comments(comments)
                    .title(createArticleRequest.title())
                    .content(createArticleRequest.content())
                    .category(createArticleRequest.category())
                    .watchNum(createArticleRequest.watchNum())
                    .build();
            articleRepository.save(article);
            user.addArticle(article);
        });
    }

    public ArticleDetailResponse getArticleDetail(Long articleId, Long userId) {
        Article article = judgeArticle(articleId, userId);
        Long timestamp = article.getCreateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return ArticleDetailResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory())
                .watchNum(article.getWatchNum())
                .createTime(timestamp)
                .username(article.getUser().getUsername())
                .build();
    }


    public void deleteArticle(Long articleId, Long userId) {
        Article article = judgeArticle(articleId, userId);
        articleRepository.delete(article);
    }

    public void updateArticle(Long articleId, Long userId, UpdateArticleRequest request) {
        Article article = judgeArticle(articleId, userId);
        if (request.category() != null) article.setCategory(request.category());
        if (request.watchNum() != null) article.setWatchNum(request.watchNum());
        if (request.title() != null) article.setTitle(request.title());
        if (request.content() != null) article.setContent(request.content());
        articleRepository.save(article);
    }

}
