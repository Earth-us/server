package com.greenity.server.chat.repository;

import com.greenity.server.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE (c.creater.id = :createrId AND c.entrant.id = :entrantId) OR (c.creater.id = :entrantId AND c.entrant.id = :createrId) ORDER BY c.id DESC")
    Chat findChatBy(Long createrId, Long entrantId);

    @Query("SELECT c FROM Chat c WHERE c.creater.id = :userId OR c.entrant.id = :userId ORDER BY c.id DESC")
    List<Chat> findChats(Long userId);

}
