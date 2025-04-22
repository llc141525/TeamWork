package org.example.teamwork.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.repository.CommentRepository;
import org.example.teamwork.repository.UserRepository;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /*更新一篇文章*/

}
