package org.example.teamwork.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
@AllArgsConstructor
public class ArticleDetailDto implements Serializable {
    @PositiveOrZero
    Long id;
    String title;
    String content;
    String category;
    @Positive(message = "观看数应该是正整数")
    @PositiveOrZero
    Integer watchNum;
    List<Comment> comments;
    //    @NotNull(message = "不能没有作者")
    User user;

    public ArticleDetailDto() {
        this.id = 0L;
        this.title = "";
        this.content = "";
        this.category = "";
        this.watchNum = 1;
        this.comments = null;
        this.user = null;
    }
}
