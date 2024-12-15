package com.greenity.server.repository;

import com.greenity.server.dto.ActivityParticipantDTO;
import com.greenity.server.model.Participate;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long> {


    //특정 활동의 참여자 목록 조회
    //List<Participate> FindByActivityId(Long activityId);

    //특정 활동의 리더 조회
   // Optional<Participate> findByActivityIdAndRole(Long activityId, Participate.Role role);

    //특정 사용자와 활동의  참여 정보 조회
    //Optional<Participate> findByActivityIdAndUserId(Long activityId, Long userId);

    //특정 활동의 리더 user id 가져오기
    @Query("SELECT p.user.id FROM Participate p WHERE p.activity.id = :activityId and p.role = 'leader'")
    Optional<Long> isLeader(@Param("activityId")Long activityId);

    @Query("SELECT new com.greenity.server.dto.ActivityParticipantDTO(p.user.id, p.user.nickname, p.role) " +
            "FROM Participate p " +
            "WHERE p.activity.id = :activityId")
    List<ActivityParticipantDTO> findParticipantsByActivityId(@Param("activityId") Long activityId);


}
