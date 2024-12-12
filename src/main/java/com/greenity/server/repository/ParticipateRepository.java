package com.greenity.server.repository;

import com.greenity.server.model.Participate;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
