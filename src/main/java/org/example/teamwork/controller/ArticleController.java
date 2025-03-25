package org.example.teamwork.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.teamwork.DTO.ArticleDetailDto;
import org.example.teamwork.Model.Article;
import org.example.teamwork.Util.response.ApiResponse;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "文章相关API", description = "文章")
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;


    @Operation(summary = "获取文章详情",
            description = "获取文章详情")
    @GetMapping("/detail")
    public ApiResponse<List<ArticleDetailDto>> getArticleDetail() {
        List<ArticleDetailDto> articleDetailDtos = new ArrayList<>();
        articleRepository.findAll().forEach(article -> {
            ArticleDetailDto articleDetailDto = new ArticleDetailDto(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCategory(),
                    article.getWatchNum(),
                    article.getComments(),
                    article.getUser()
            );
            articleDetailDtos.add(articleDetailDto);
        });
        return ApiResponse.success(articleDetailDtos);
    }


    @Operation(summary = "获取文章列表",
            description = "获取文章列表")
    @GetMapping("/show")
    public ApiResponse<List<ArticleDetailDto>> getArticleShow() {
        List<ArticleDetailDto> articleDetailDtos = new ArrayList<>();
        articleRepository.findAll().forEach(article -> {
            ArticleDetailDto articleDetailDto = new ArticleDetailDto(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCategory(),
                    article.getWatchNum(),
                    article.getComments(),
                    article.getUser()
            );
            articleDetailDtos.add(articleDetailDto);
        });
        return ApiResponse.success(articleDetailDtos);
    }

    @Operation(summary = "新建一个文章", description = "新建一个文章")
    @PostMapping("/create")
    public ApiResponse<?> createArticle(@RequestBody ArticleDetailDto articleDetailDto) {
        Article article = new Article();
        return articleService.setArticle(articleDetailDto, article);
    }

    @Operation(summary = "删除一个文章", description = "删除一个文章")
    @DeleteMapping("/delete")
    public ApiResponse<String> deleteArticle(@RequestParam Long id) {
        articleRepository.deleteById(id);
        return ApiResponse.success("success");
    }

    @Operation(summary = "更新一个文章", description = "更新一个文章")
    @PutMapping("/update")
    public ApiResponse<?> updateArticle(@RequestParam Long id, @RequestBody ArticleDetailDto articleDetailDto) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ApiResponse.error(404, "文章不存在");
        }
        return articleService.setArticle(articleDetailDto, article);
    }


}

