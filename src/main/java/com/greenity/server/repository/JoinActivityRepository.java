package com.greenity.server.repository;

import com.greenity.server.model.JoinActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JoinActivityRepository extends JpaRepository<JoinActivity, Long> {

    //특정 활동의 모든 신청 정보 조회
    List<JoinActivity> findByActivityId(Long ActivityId);

    //특정 활동과 사용자의 신청 정보 조회
    Optional<JoinActivity> findByActivityIdAndUserId(Long ActivityId, Long UserId);

    //특정 활동의 특정 상태 (pending, accepted,rejected) 에 해당하는 모든 정보 조회
    List<JoinActivity> findByActivityIdAndIsAccepted(Long ActivityId, JoinActivity.JoinStatus status);

}
