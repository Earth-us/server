package com.greenity.server.community.dto.response;

import com.greenity.server.user.domain.User;
import lombok.Builder;

@Builder
public record WriterResponse(
        Long writerId,
        String nickname  //User 완성 후 추가 예정
) {
    public static WriterResponse from(User user) {
        return WriterResponse.builder()
                .writerId(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
