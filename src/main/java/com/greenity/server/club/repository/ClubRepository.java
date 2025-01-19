package com.greenity.server.club.repository;

import com.greenity.server.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository <Club, Long> {
}
