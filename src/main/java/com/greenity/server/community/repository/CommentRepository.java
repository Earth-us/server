package com.greenity.server.community.repository;

import com.greenity.server.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c where c.commenter.id = :userId order by c.id DESC ")
    List<Comment> findAllByUserId(Long userId);

    @Query("SELECT c FROM Comment c where c.originalWriting.id = :writingId order by c.id DESC ")
    List<Comment> findAllByWritingId(Long writingId);

}
