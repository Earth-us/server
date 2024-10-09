package com.greenity.server.community.dto.request;

public record SearchRequest(
        String nickname,
        String title,
        String content
) {
}
