package org.example.teamwork.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;
import org.example.teamwork.Model.Article;
import org.example.teamwork.Model.User;

import java.io.Serializable;

/**
 * DTO for {@link Article}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleShowDto implements Serializable {
    @PositiveOrZero
    Long id;

    @NotNull(message = "标题不为空")
    String title;

    @NotNull(message = "类型不能为空")
    String category;

    @NotNull(message = "分页不为空")
    @PositiveOrZero
    Integer page;

    @NotNull(message = "观看数量不能为空")
    @PositiveOrZero
    Integer watchNum;

    @NotNull(message = "文章不能没有作者")
    User user;
}

