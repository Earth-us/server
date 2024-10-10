package com.greenity.server.community.dto.response;

import com.greenity.server.community.domain.Comment;
import lombok.Builder;

@Builder
public record MyCommentResponse(
        Long writingId,
        Long commentId,
        String writingTitle,
        String content
) {
    public static MyCommentResponse from(Comment comment) {
        return MyCommentResponse.builder()
                .writingId(comment.getOriginalWriting().getId())
                .commentId(comment.getId())
                .writingTitle(comment.getOriginalWriting().getTitle())
                .content(comment.getContent())
                .build();
    }
}
