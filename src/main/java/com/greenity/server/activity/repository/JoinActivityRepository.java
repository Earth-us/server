package com.greenity.server.activity.repository;

import com.greenity.server.activity.dto.JoinActivityResponseDTO;
import com.greenity.server.activity.entity.JoinActivity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JoinActivityRepository extends JpaRepository<JoinActivity, Long> {

    //특정 활동과 사용자의 신청 정보 조회
    Optional<JoinActivity> findByActivityIdAndUserId(Long ActivityId, Long UserId);


    //특정 활동의 PENDING 상태 신청 목록 조회
    @Query("SELECT new com.greenity.server.dto.JoinActivityResponseDTO(j.user.id, u.name, j.isAccepted) " +
            "FROM JoinActivity j " +
            "JOIN User u ON j.user.id = u.id " +
            "WHERE j.activity.id = :activityId AND j.isAccepted = 'PENDING'")
    List<JoinActivityResponseDTO> findPendingList(@Param("activityId") Long activityId);

    //특정 사용자의 신청 목록 조회
    @Query("SELECT j FROM JoinActivity j WHERE j.user.id = :userId")
    List<JoinActivity> findByUserId(@Param("userId") Long userId);




}
