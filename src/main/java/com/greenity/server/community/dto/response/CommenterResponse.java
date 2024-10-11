package com.greenity.server.community.dto.response;

import com.greenity.server.user.domain.User;
import lombok.Builder;

@Builder
public record CommenterResponse(
        Long commenterId,
        String commenterNickname
) {
    public static CommenterResponse from(User user) {
        return CommenterResponse.builder()
                .commenterId(user.getId())
                .commenterNickname(user.getNickname())
                .build();
    }
}
