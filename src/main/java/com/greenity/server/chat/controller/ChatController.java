package com.greenity.server.chat.controller;

import com.greenity.server.chat.dto.response.ChatInfoResponse;
import com.greenity.server.chat.service.ChatService;
import com.greenity.server.global.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    final private ChatService chatService;

    @GetMapping("/community/chat")
    @ResponseBody
    public ResponseTemplate<?> findChats(Long userId) {
        List<ChatInfoResponse> chatInfoResponseList = chatService.findChats(userId);
        return ResponseTemplate.from(chatInfoResponseList);
    }

    @PostMapping("/community/chat/{profileId}")
    @ResponseBody
    public ResponseTemplate<?> createChat(Long userId, @PathVariable Long profileId) {
        Long chatId = chatService.createChat(userId, profileId);
        return ResponseTemplate.from(chatId);
    }

}
