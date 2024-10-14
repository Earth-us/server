package com.greenity.server.chat.dto.response;

import java.util.List;

public record ChatInfoResponseList(
        List<ChatInfoResponse> chatInfoResponseList
) {
    public static ChatInfoResponseList from(List<ChatInfoResponse> chatInfoResponseList) {
        return new ChatInfoResponseList(chatInfoResponseList);
    }
}
