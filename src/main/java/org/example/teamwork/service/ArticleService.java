package org.example.teamwork.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.example.teamwork.DTO.request.ArticleDetailRequest;
import org.example.teamwork.Mapper.ArticleMapper;
import org.example.teamwork.Model.Article;
import org.example.teamwork.Model.User;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.repository.CommentRepository;
import org.example.teamwork.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@CommonsLog
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /*更新一篇文章*/
    @Transactional
    public void saveArticle(@RequestBody ArticleDetailRequest articleDetailRequest) {
        Article article = articleMapper.toEntity(articleDetailRequest);
        Optional<User> userRepositoryById = userRepository.findById(articleDetailRequest.getUserId());
        try {
            articleDetailRequest.getCommentsId().forEach(
                    commentId -> {
                        Optional<org.example.teamwork.Model.Comment> commentById = commentRepository.findById(commentId);
                        commentById.ifPresent(article::addComment);
                    });
            if (userRepositoryById.isEmpty()) {
                log.error("用户不存在");
            } else {
                article.setUser(userRepositoryById.get());
                articleRepository.save(article);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
