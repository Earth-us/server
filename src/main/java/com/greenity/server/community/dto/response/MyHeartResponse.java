package com.greenity.server.community.dto.response;

import com.greenity.server.community.domain.Heart;
import lombok.Builder;

@Builder
public record MyHeartResponse(
        Long writingId,
        Long heartId,
        String writingTitle
) {
    public static MyHeartResponse from(Heart heart) {
        return MyHeartResponse.builder()
                .writingId(heart.getOriginalWriting().getId())
                .heartId(heart.getId())
                .writingTitle(heart.getOriginalWriting().getTitle())
                .build();
    }
}
