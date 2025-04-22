package org.example.teamwork.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.teamwork.repository.ArticleRepository;
import org.example.teamwork.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "文章相关API", description = "文章")
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
//    private final ArticleMapper articleMapper;
//
//    @Operation(summary = "获取文章首页", description = "获取文章首页")
//    @GetMapping("/show")
//    public ApiResponse<List<ArticleShowDto>> getArticleDetail() {
//        List<ArticleShowDto> articleShowDto = new ArrayList<>();
//        articleRepository.findAll().forEach(article -> {
//            ArticleShowDto articleDetailDto = articleMapper.toShowDto(article);
//            BeanUtils.copyProperties(article, articleDetailDto);
//            articleShowDto.add(articleDetailDto);
//        });
//        return ApiResponse.success(articleShowDto);
//    }
//
//    @Operation(summary = "根据 id 获取文章")
//    @GetMapping("/detail/{id}")
//    public ApiResponse<ArticleDetailRequest> getArticleDetailById(@PathVariable Long id) {
//        Article article = articleRepository.findById(id).orElse(null);
//        ArticleDetailRequest articleDetailRequest = articleMapper.toDetailDto(article);
//        if (article == null) {
//            return ApiResponse.error(404, "文章不存在");
//        } else {
//            return ApiResponse.success(articleDetailRequest);
//        }
//    }
//
//    @Operation(summary = "新建一个文章", description = "新建一个文章")
//    @PostMapping("/create")
//    public ApiResponse<String> createArticle(@RequestBody ArticleDetailRequest articleDetailRequest) {
//        articleService.saveArticle(articleDetailRequest);
//        return ApiResponse.success("文章创建成功");
//    }

//    @Operation(summary = "删除一个文章", description = "删除一个文章")
//    @DeleteMapping("/delete/{id}")
//    public ApiResponse<String> deleteArticle(@RequestParam Long id) {
//        try {
//            articleRepository.deleteById(id);
//            return ApiResponse.success("删除成功");
//        } catch (Exception e) {
//            return ApiResponse.error(400, "文章删除失败");
//        }
//    }

//    @Operation(summary = "更新一个文章", description = "更新一个文章")
//    @PutMapping("/update/{id}")
//    public ApiResponse<String> updateArticle(@RequestParam Long id, @RequestBody ArticleDetailRequest articleDetailRequest) {
//        Article article = articleRepository.findById(id).orElse(null);
//        if (article == null) {
//            return ApiResponse.error(404, "文章不存在");
//        } else {
//            BeanUtils.copyProperties(articleDetailRequest, article, "id");
//            try {
//                articleRepository.save(article);
//                return ApiResponse.success("更新成功");
//            } catch (Exception e) {
//                return ApiResponse.error(400, "文章更新失败");
//            }
//        }
//    }

}

