package org.example.teamwork.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.teamwork.DTO.ArticleDetailDto;
import org.example.teamwork.DTO.ArticleShowDto;
import org.example.teamwork.Model.Article;
import org.example.teamwork.Util.response.ApiResponse;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.service.ArticleService;
import org.springframework.beans.BeanUtils;
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


    @Operation(summary = "获取文章首页", description = "获取文章首页")
    @GetMapping("/show")
    public ApiResponse<List<ArticleShowDto>> getArticleDetail() {
        List<ArticleShowDto> articleShowDto = new ArrayList<>();
        articleRepository.findAll().forEach(article -> {
            ArticleShowDto articleDetailDto = new ArticleShowDto();
            BeanUtils.copyProperties(article, articleDetailDto);
            articleShowDto.add(articleDetailDto);
        });
        return ApiResponse.success(articleShowDto);
    }

    @Operation(summary = "根据 id 获取文章")
    @GetMapping("/detail/{id}")
    public ApiResponse<ArticleDetailDto> getArticleDetailById(@PathVariable Long id) {
        ArticleDetailDto articleDetailDto = new ArticleDetailDto();
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ApiResponse.error(404, "文章不存在");
        } else {
            return ApiResponse.success(articleDetailDto);
        }

    }

    @Operation(summary = "新建一个文章", description = "新建一个文章")
    @PostMapping("/create")
    public ApiResponse<String> createArticle(@RequestBody ArticleDetailDto articleDetailDto) {
        articleService.saveArticle(articleDetailDto);
        return ApiResponse.success("文章创建成功");
    }

    @Operation(summary = "删除一个文章", description = "删除一个文章")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteArticle(@RequestParam Long id) {
        try {
            articleRepository.deleteById(id);
            return ApiResponse.success("删除成功");
        } catch (Exception e) {
            return ApiResponse.error(400, "文章删除失败");
        }
    }

    @Operation(summary = "更新一个文章", description = "更新一个文章")
    @PutMapping("/update/{id}")
    public ApiResponse<String> updateArticle(@RequestParam Long id, @RequestBody ArticleDetailDto articleDetailDto) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ApiResponse.error(404, "文章不存在");
        } else {
            BeanUtils.copyProperties(articleDetailDto, article, "id");
            try {
                articleRepository.save(article);
                return ApiResponse.success("更新成功");
            } catch (Exception e) {
                return ApiResponse.error(400, "文章更新失败");
            }
        }
    }

}

