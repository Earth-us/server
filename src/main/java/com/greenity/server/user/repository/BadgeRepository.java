package com.greenity.server.user.repository;

import com.greenity.server.badge.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("SELECT b FROM Badge b WHERE b.owner.id = :userId")
    List<Badge> findByOwnerId(Long userId);

}
