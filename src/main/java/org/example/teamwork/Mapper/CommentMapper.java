package org.example.teamwork.Mapper;

import org.example.teamwork.DTO.request.CommentRequest;
import org.example.teamwork.Model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment requestToEntity(CommentRequest commentRequest);

    CommentRequest entityToRequest(Comment comment);
}
