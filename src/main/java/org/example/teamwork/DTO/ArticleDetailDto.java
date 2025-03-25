package org.example.teamwork.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;
import org.example.teamwork.Model.Comment;
import org.example.teamwork.Model.User;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.example.teamwork.Model.Article}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDetailDto implements Serializable {
    @NotNull
    @PositiveOrZero
    Long id;
    @NotNull(message = "文章不能没有标题")
    String title;
    String content;
    @NotNull(message = "不能没有类别")
    String category;
    @NotNull
    @Positive(message = "观看数应该是正整数")
    @PositiveOrZero
    Integer watchNum;
    List<Comment> comments;
    //    @NotNull(message = "不能没有作者")
    User user;
}
