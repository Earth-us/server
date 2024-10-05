package com.greenity.server.community.dto.request;

public record WritingRequest(
        String title,
        String content,
        String writing_pic
) {
}
