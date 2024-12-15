package com.greenity.server.repository;

import com.greenity.server.dto.JoinActivityResponseDTO;
import com.greenity.server.model.JoinActivity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JoinActivityRepository extends JpaRepository<JoinActivity, Long> {

    //특정 활동의 모든 신청 정보 조회
    List<JoinActivity> findByActivityId(Long ActivityId);

    //특정 활동과 사용자의 신청 정보 조회
    Optional<JoinActivity> findByActivityIdAndUserId(Long ActivityId, Long UserId);

    //특정 활동의 특정 상태 (pending, accepted,rejected) 에 해당하는 모든 정보 조회
    List<JoinActivity> findByActivityIdAndIsAccepted(Long ActivityId, JoinActivity.JoinStatus status);

    //특정 활동의 PENDING 상태 신청 목록 조회
    @Query("SELECT new com.greenity.server.dto.JoinActivityResponseDTO(j.user.id, u.name, j.isAccepted) " +
            "FROM JoinActivity j " +
            "JOIN User u ON j.user.id = u.id " +
            "WHERE j.activity.id = :activityId AND j.isAccepted = 'PENDING'")
    List<JoinActivityResponseDTO> findPendingList(@Param("activityId") Long activityId);


}
