package com.greenity.server.community.dto.response;

import com.greenity.server.community.domain.Writing;
import lombok.Builder;

@Builder
public record WritingResponse(
        Long writingId,
        String writingTitle,
        String writingContent,
        String writingPic,
        Long heartCount,
        WriterResponse writer
) {
    public static WritingResponse from(Writing writing) {
        return WritingResponse.builder()
                .writingId(writing.getId())
                .writingTitle(writing.getTitle())
                .writingContent(writing.getContent())
                .writingPic(writing.getWriting_pic())
                .heartCount(writing.getHeartCount())
                .writer(WriterResponse.from(writing.getWriter()))
                .build();
    }
}
