package com.greenity.server.repository;

import com.greenity.server.model.ActivityRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRoundRepository extends JpaRepository<ActivityRound, Long> {

    List<ActivityRound> findByActivityId(Long activityID);

    Optional<ActivityRound> findByActivityIdAndActivityNum(Long activityId, Long activityNum);
}
