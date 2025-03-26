package org.example.teamwork.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest implements Serializable {
    private String content;
    private Long articleId;
    private Long userId;

}
