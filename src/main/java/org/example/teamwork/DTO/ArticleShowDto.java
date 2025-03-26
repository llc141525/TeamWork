package org.example.teamwork.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Value;
import org.example.teamwork.Model.Article;
import org.example.teamwork.Model.User;

import java.io.Serializable;

/**
 * DTO for {@link Article}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ArticleShowDto implements Serializable {
    @PositiveOrZero
    Long id;

    String title;

    String category;

    @PositiveOrZero
    Integer page;


    @PositiveOrZero
    Integer watchNum;

    User user;

    public ArticleShowDto() {
        this.id = 0L;
        this.title = "";
        this.category = "";
        this.page = 1;
        this.watchNum = 1;
        this.user = null;

    }
}

