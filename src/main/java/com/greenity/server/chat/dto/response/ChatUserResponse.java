package com.greenity.server.chat.dto.response;

import com.greenity.server.user.domain.User;
import lombok.Builder;

@Builder
public record ChatUserResponse(
        Long id,
        String nickname
) {
    public static ChatUserResponse from(User user) {
        return ChatUserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
