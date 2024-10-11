package com.greenity.server.community.dto.response;

import java.util.List;

public record WritingDetailResponse(
        WritingResponse writing,
        List<CommentResponse> commentResponseList
) {
    public static WritingDetailResponse from(WritingResponse writing, List<CommentResponse> commentResponseList) {
        return new WritingDetailResponse(writing, commentResponseList);
    }
}
