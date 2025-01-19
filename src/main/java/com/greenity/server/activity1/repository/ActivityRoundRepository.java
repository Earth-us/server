package com.greenity.server.activity1.repository;

import com.greenity.server.activity1.model.ActivityRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRoundRepository extends JpaRepository<ActivityRound, Long> {

    @Query("SELECT ar FROM ActivityRound ar " +
            "JOIN ar.activity a " +
            "JOIN Participate p ON p.activity = a " +
            "WHERE p.user.id = :userId " +
            "ORDER BY ar.activityDate ASC")
    List<ActivityRound> findActivityRoundsByUserId(Long userId);
}
