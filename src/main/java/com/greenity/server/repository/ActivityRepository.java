package com.greenity.server.repository;


import com.greenity.server.model.Activity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a " +
            "WHERE (:isEnd IS NULL OR a.isEnd = :isEnd) " +
            "AND (:category IS NULL OR a.category = :category) " +
            "AND (:activityMethod IS NULL OR a.activityMethod = :activityMethod)")
    List<Activity> findActivities(
            @Param("isEnd") Boolean isEnd,
            @Param("category") Activity.Category category,
            @Param("activityMethod") Activity.ActivityMethod activityMethod
    );

}