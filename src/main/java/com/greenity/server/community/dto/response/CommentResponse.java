package com.greenity.server.community.dto.response;

import com.greenity.server.community.domain.Comment;
import lombok.Builder;

@Builder
public record CommentResponse(
        Long commentId,
        String content,
        CommenterResponse commenter
) {
    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .commenter(CommenterResponse.from(comment.getCommenter()))
                .build();
    }
}
