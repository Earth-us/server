package com.greenity.server.service;

import com.greenity.server.model.Activity;
import com.greenity.server.model.JoinActivity;
import com.greenity.server.model.Participate;
import com.greenity.server.model.User;
import com.greenity.server.repository.ActivityRepository;
import com.greenity.server.repository.JoinActivityRepository;
import com.greenity.server.repository.ParticipateRepository;
import com.greenity.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JoinActivityService {
//    사용자가 활동에 신청할 때 JoinActivity 레코드 생성.
//    리더가 신청을 승인/거절할 수 있음.
//    특정 상태(PENDING, ACCEPTED, REJECTED)의 신청 정보를 조회.
    private final JoinActivityRepository joinActivityRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ParticipateRepository participateRepository;

    public JoinActivityService(
            JoinActivityRepository joinActivityRepository,
            ActivityRepository activityRepository,
            UserRepository userRepository,
            ParticipateRepository participateRepository) {
        this.joinActivityRepository = joinActivityRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.participateRepository = participateRepository;
    }

    //신청 생성
    @Transactional
    public String createJoinActivity(Long activityId, Long userId) {

        System.out.println("[DEBUG] 요청된 Activity ID: " + activityId);
        System.out.println("[DEBUG] 요청된 User ID: " + userId);

        //활동 존재 여부 확인
       Activity activity = activityRepository.findById(activityId)
               .orElseThrow(()->new IllegalArgumentException("존재하지 않는 활동입니다."));
        System.out.println("[DEBUG] 활동 조회 성공: " + activity.getTitle());


       //최대 인원 초과 여부 확인
        if(activity.getCurrentParticipants()>=activity.getRecruitNum()){
            System.out.println("[DEBUG] 현재 인원 초과: " + activity.getCurrentParticipants() + "/" + activity.getRecruitNum());
            throw new IllegalArgumentException("활동 신청 가능 인원을 초과했습니다.");
        }
        System.out.println("[DEBUG] : 인원 초과 X");
        System.out.println("[DEBUG] 요청된 userId: " + userId);


       //사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
        System.out.println("[DEBUG] 사용자 조회 성공: " + user.getName());

        //이미 신청한 활동인지 확인
        if(joinActivityRepository.findByActivityIdAndUserId(activityId, userId).isPresent()) {
            System.out.println("[DEBUG] 이미 신청된 활동입니다.");
            throw new IllegalArgumentException("이미 신청한 활동입니다.");
        }
        //신청 생성
        JoinActivity joinActivity = new JoinActivity();
        joinActivity.setActivity(activity);
        joinActivity.setUser(user);

        //승인 확인 필요 여부에 따라 상태 결정
        if(Boolean.TRUE.equals(activity.getIsApprovalRequired())){
            joinActivity.setIsAccepted(JoinActivity.JoinStatus.pending); //승인
            joinActivityRepository.save(joinActivity); //join_activity에 저장
            return "PENDING";
        }else{
            joinActivity.setIsAccepted(JoinActivity.JoinStatus.accepted); //승인 불필요
            joinActivityRepository.save(joinActivity); //join_activity에 저장

            //participate에 저장
            Participate participate = new Participate();
            participate.setUser(user);
            participate.setActivity(activity);
            participate.setRole(Participate.Role.MEMBER);
            participateRepository.save(participate);

            //해당 활동의 참여자 수 증가
            activity.setCurrentParticipants(activity.getCurrentParticipants() + 1);
            activityRepository.save(activity);


            return "ACCEPTED";

        }

    }


}
