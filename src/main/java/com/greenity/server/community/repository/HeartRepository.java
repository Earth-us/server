package com.greenity.server.community.repository;

import com.greenity.server.community.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query("SELECT h FROM Heart h WHERE h.heartUser.id = :userId AND h.originalWriting.id = :writingId")
    Heart findHeartByUserAndWriting(Long userId, Long writingId);

}
