package com.greenity.server.chat.dto.response;

import com.greenity.server.chat.domain.Chat;
import lombok.Builder;

@Builder
public record ChatInfoResponse(
        Long chatId,
        ChatUserResponse creater,
        ChatUserResponse entrant
) {
    public static ChatInfoResponse from(Chat chat) {
        return ChatInfoResponse.builder()
                .chatId(chat.getId())
                .creater(ChatUserResponse.from(chat.getCreater()))
                .entrant(ChatUserResponse.from(chat.getEntrant()))
                .build();
    }
}
