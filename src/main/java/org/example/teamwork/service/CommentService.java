package org.example.teamwork.service;

import lombok.RequiredArgsConstructor;
import org.example.teamwork.dto.request.CreateCommentRequest;
import org.example.teamwork.dto.response.GetCommentsResponse;
import org.example.teamwork.model.Comment;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.repository.CommentRepository;
import org.example.teamwork.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public void createComment(Long articleId, CreateCommentRequest request, Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            articleRepository.findById(articleId).ifPresent(article -> {
                Comment comment = Comment.builder()
                        .stars(request.stars())
                        .content(request.content())
                        .user(user)
                        .article(article)
                        .build();
                // 保存
                commentRepository.save(comment);

                // 维护双向关系
                article.addComment(comment);
                user.addComment(comment);
            });
        });
    }

    public List<GetCommentsResponse> getComments(Long articleId) {
        List<GetCommentsResponse> comments = new ArrayList<>();
        // 获取文章
        articleRepository.findById(articleId).ifPresent(article -> {
            // 根据文章获取所有的评论
            article.getComments().forEach(comment -> {
                // 转换成时间戳
                Long createTime = comment.getCreateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                GetCommentsResponse response = GetCommentsResponse.builder()
                        .createTime(createTime)
                        .username(comment.getUser().getUsername()) // 只需要用户名
                        .content(comment.getContent())
                        .stars(comment.getStars())
                        .build();
                comments.add(response);
            });
        });
        return comments;
    }

}
