package com.greenity.server.chat.controller;

import com.greenity.server.chat.service.ChatService;
import com.greenity.server.global.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    final private ChatService chatService;

    @PostMapping("/community/chat/{profileId}")
    @ResponseBody
    public ResponseTemplate<?> createChat(Long userId, @PathVariable Long profileId) {
        Long chatId = chatService.createChat(userId, profileId);
        return ResponseTemplate.from(chatId);
    }

}
