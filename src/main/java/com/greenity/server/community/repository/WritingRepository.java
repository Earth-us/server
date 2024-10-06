package com.greenity.server.community.repository;

import com.greenity.server.community.domain.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
