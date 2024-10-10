package com.greenity.server.community.dto.response;

import com.greenity.server.community.domain.Writing;
import lombok.Builder;

@Builder
public record MyWritingResponse(
        Long writingId,
        String writingTitle,
        Long heartCount
) {
    public static MyWritingResponse from(Writing writing) {
        return MyWritingResponse.builder()
                .writingId(writing.getId())
                .writingTitle(writing.getTitle())
                .heartCount(writing.getHeartCount())
                .build();
    }
}
