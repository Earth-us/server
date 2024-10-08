package com.greenity.server.community.repository;

import com.greenity.server.community.domain.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Writing w SET w.heartCount = w.heartCount + 1 WHERE w.id = :writingId")
    void upHeartCount(Long writingId);

    @Modifying
    @Transactional
    @Query("UPDATE Writing w SET w.heartCount = w.heartCount - 1 WHERE w.id = :writingId")
    void downHeartCount(Long writingId);

    @Query("SELECT w FROM Writing w WHERE w.writer.nickname LIKE %:nickname% AND w.title LIKE %:title% AND w.content LIKE %:content% ORDER BY w.id DESC")
    List<Writing> searchWritings(String nickname, String title, String content);

    List<Writing> findAllByOrderByIdDesc();

    @Query("SELECT w FROM Writing w where w.writer.id = :userId order by w.id DESC ")
    List<Writing> findAllByUserId(Long userId);

}
