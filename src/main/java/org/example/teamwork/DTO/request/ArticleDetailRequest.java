package org.example.teamwork.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link org.example.teamwork.Model.Article}
 * 扁平化 DTO 设计, 只传递实体类对应的id, 避免出现无限递归.
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@Builder
public class ArticleDetailRequest implements Serializable {
    @Builder.Default
    @Schema(description = "title", example = "默认标题")
    String title = "默认标题";


    @Builder.Default
    @Schema(description = "content", example = "默认文章")
    String content = "默认文章";


    @Builder.Default
    @Schema(description = "category", example = "默认分类")
    String category = "默认分类";


    @PositiveOrZero
    @Builder.Default
    @Schema(description = "watchNum", example = "1")
    Integer watchNum = 1;


    //    @Singular
    @Schema(description = "评论的id", example = "[1]", type = "array")
    List<Long> commentsId = new ArrayList<>();


    @Builder.Default
    @Schema(description = "文章对应的用户的id", example = "1")
    Long userId = 1L;
}
