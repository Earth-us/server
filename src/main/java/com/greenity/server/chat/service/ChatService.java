package com.greenity.server.chat.service;

import com.greenity.server.chat.domain.Chat;
import com.greenity.server.chat.dto.response.ChatInfoResponse;
import com.greenity.server.chat.repository.ChatRepository;
import com.greenity.server.user.domain.User;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public List<ChatInfoResponse> findChats(Long userId) {
        return chatRepository.findChats(userId).stream()
                .map(ChatInfoResponse::from)
                .toList();
    }

    public Long createChat(Long userId, Long profileId) {
        User creater = userRepository.findById(userId).orElse(null);
        User entrant = userRepository.findById(profileId).orElse(null);
        Chat chat;
        if(chatRepository.findChatBy(userId, profileId) != null) {
            chat = chatRepository.findChatBy(userId, profileId);
        }
        else {
            chat = Chat.builder()
                    .creater(creater)
                    .entrant(entrant)
                    .build();
            chatRepository.save(chat);
        }
        return chat.getId();
    }

    public void deleteChat(Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElse(null);
        chatRepository.delete(chat);
    }

}
