package com.greenity.server.chat.dto.response;

import com.greenity.server.user.domain.User;
import lombok.Builder;

@Builder
public record ProfileResponse(
        Long profileId,
        String nickname
        // User 관련 정보(활동 이력 등) 추후 추가
) {
    public static ProfileResponse from(User user) {
        return ProfileResponse.builder()
                .profileId(user.getId())
                .nickname(user.getNickname())
                .build(); // User 관련 정보(활동 이력 등) 추후 추가
    }
}
