package org.example.teamwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.teamwork.dto.request.CreateCommentRequest;
import org.example.teamwork.dto.response.GetCommentsResponse;
import org.example.teamwork.service.CommentService;
import org.example.teamwork.utils.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{articleId}")
    public ApiResponse<String> createComment(@PathVariable Long articleId, @RequestBody CreateCommentRequest request, @RequestAttribute Long userId) {
        commentService.createComment(articleId, request, userId);
        return ApiResponse.success(null);
    }

    @GetMapping("/{articleId}")
    public ApiResponse<List<GetCommentsResponse>> getComments(@PathVariable Long articleId) {
        List<GetCommentsResponse> response = commentService.getComments(articleId);
        return ApiResponse.success(response);
    }

}
