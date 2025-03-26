package org.example.teamwork.Mapper;

import org.example.teamwork.DTO.request.ArticleDetailRequest;
import org.example.teamwork.DTO.request.ArticleShowDto;
import org.example.teamwork.Model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toEntity(ArticleDetailRequest articleDetailRequest);

    ArticleDetailRequest toDetailDto(Article article);

    ArticleShowDto toShowDto(Article article);
}
