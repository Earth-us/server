package com.greenity.server.chat.service;

import com.greenity.server.chat.domain.Chat;
import com.greenity.server.chat.repository.ChatRepository;
import com.greenity.server.user.domain.User;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Long createChat(Long userId, Long profileId) {
        User creater = userRepository.findById(userId).orElse(null);
        User entrant = userRepository.findById(profileId).orElse(null);
        Chat chat = Chat.builder()
                .creater(creater)
                .entrant(entrant)
                .build();
        chatRepository.save(chat);
        return chat.getId();
    }

}
