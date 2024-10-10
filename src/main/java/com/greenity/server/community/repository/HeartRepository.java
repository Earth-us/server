package com.greenity.server.community.repository;

import com.greenity.server.community.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query("SELECT h FROM Heart h WHERE h.heartUser.id = :userId AND h.originalWriting.id = :writingId")
    Heart findHeartByUserAndWriting(Long userId, Long writingId);

    @Query("SELECT h FROM Heart h where h.heartUser.id = :userId order by h.id DESC ")
    List<Heart> findAllByUserId(Long userId);

}
