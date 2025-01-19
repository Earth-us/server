package com.greenity.server.activity.repository;

import com.greenity.server.activity.entity.ActivityRound;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRoundRepository extends JpaRepository<ActivityRound, Long> {

    List<ActivityRound> findByActivityId(Long activityID);


    @Query("SELECT COUNT(ar) > 0 FROM ActivityRound ar WHERE ar.activity.id = :activityId AND ar.activityNum = :activityNum")
    boolean existsByActivityIdAndActivityNum(@Param("activityId") Long activityId, @Param("activityNum") Long activityNum);


    Optional<ActivityRound> findByActivityIdAndActivityNum(Long activityId, Long activityNum);

    @Modifying // JPQL 수정 쿼리를 실행할 때 필수
    @Transactional // 트랜잭션 컨텍스트 보장
    @Query("DELETE FROM ActivityRound ar WHERE ar.activity.id = :activityId")
    void deleteByActivityId(@Param("activityId") Long activityId);

    Optional<ActivityRound> findByActivityIdAndActivityNum(Long activityId, int activityNum);


}
