package org.example.teamwork.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.teamwork.dto.request.CreateArticleRequest;
import org.example.teamwork.dto.request.UpdateArticleRequest;
import org.example.teamwork.dto.response.ArticleDetailResponse;
import org.example.teamwork.dto.response.ArticleShowResponse;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.service.ArticleService;
import org.example.teamwork.utils.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章相关API", description = "文章")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;


    @Operation(summary = "获取文章首页", description = "获取文章首页")
    @GetMapping("/show")
    public ApiResponse<List<ArticleShowResponse>> getArticleShow(@RequestAttribute Long userId) {
        List<ArticleShowResponse> res = articleService.getArticleShow(userId);
        return ApiResponse.success(res);
    }


    @Operation(summary = "新建一个文章", description = "新建一个文章")
    @PostMapping({"/", ""})
    public ApiResponse<String> createArticle(@RequestBody CreateArticleRequest articleDetailRequest, @RequestAttribute Long userId) {
        articleService.saveArticle(articleDetailRequest, userId);
        return ApiResponse.success(null);
    }


    @Operation(summary = "根据 id 获取文章")
    @GetMapping("/detail/{id}")
    public ApiResponse<ArticleDetailResponse> getArticleDetailById(@PathVariable Long id, @RequestAttribute Long userId) {
        ArticleDetailResponse articleDetail = articleService.getArticleDetail(id, userId);
        return ApiResponse.success(articleDetail);
    }


    @Operation(summary = "删除一个文章", description = "删除一个文章")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteArticle(@RequestParam Long id, @RequestAttribute Long userId) {
        articleService.deleteArticle(id, userId);
        return ApiResponse.success(null);
    }

    @Operation(summary = "更新一个文章", description = "更新一个文章")
    @PutMapping("/update/{id}")
    public ApiResponse<String> updateArticle(@RequestParam Long id,
                                             @RequestAttribute Long userId,
                                             @RequestBody UpdateArticleRequest request) {
        articleService.updateArticle(id, userId, request);
        return ApiResponse.success(null);
    }

}

