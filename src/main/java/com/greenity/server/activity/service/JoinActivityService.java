package com.greenity.server.activity.service;

import com.greenity.server.activity.dto.JoinActivityResponseDTO;
import com.greenity.server.activity.entity.Activity;
import com.greenity.server.activity.entity.JoinActivity;
import com.greenity.server.activity.entity.Participate;
import com.greenity.server.user.entity.User;
import com.greenity.server.activity.repository.ActivityRepository;
import com.greenity.server.activity.repository.JoinActivityRepository;
import com.greenity.server.activity.repository.ParticipateRepository;
import com.greenity.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            participate.setRole(Participate.Role.member);
            participateRepository.save(participate);

            //해당 활동의 참여자 수 증가
            activity.setCurrentParticipants(activity.getCurrentParticipants() + 1);
            activityRepository.save(activity);


            return "ACCEPTED";

        }

    }

    //리더가 pending list 확인
    public List<JoinActivityResponseDTO> getPending(Long activityId, Long userId){
        //리더가 맞는지 확인
        Long leaderId = participateRepository.isLeader(activityId)
                .orElseThrow(()-> new IllegalArgumentException("리더 정보가 없거나 잘못된 활동 번호입니다."));
        if(!leaderId.equals(userId)){
            throw new IllegalArgumentException("리더가 아닙니다.");
        }

        return joinActivityRepository.findPendingList(activityId);
    }

    /*
    리더가 accept
     */
    @Transactional
    public void acceptJoinRequest(Long activityId, Long userId){
        //활동과 사용자가 있는지 확인
        JoinActivity joinActivity = joinActivityRepository.findByActivityIdAndUserId(activityId, userId)
                .orElseThrow(()->new IllegalArgumentException("잘못된 신청입니다."));

        //사용자의 상태가 pending인지 확인
        if(!joinActivity.getIsAccepted().equals(JoinActivity.JoinStatus.pending)){
            throw new IllegalArgumentException("해당 신청은 이미 처리되었습니다.");
        }
        //모집 인원 초과 확인
        Activity activity = joinActivity.getActivity();
        if(activity.getCurrentParticipants() >= activity.getRecruitNum()){
            throw  new IllegalArgumentException("모집 인원을 초과하여 신청을 승인할 수 없습니다.");
        }

        //상태 변경
        joinActivity.setIsAccepted(JoinActivity.JoinStatus.accepted);
        joinActivityRepository.save(joinActivity);

        //참여자로 추가
        Participate participate = new Participate();
        participate.setActivity(joinActivity.getActivity());
        participate.setUser(joinActivity.getUser());
        participate.setRole(Participate.Role.member);
        participateRepository.save(participate);


        //해당 활동의 참여자 수 증가
        activity.setCurrentParticipants(activity.getCurrentParticipants() + 1);
        activityRepository.save(activity);

    }

    /*
    리더가 거절
     */
    @Transactional
    public void rejectJoinRequest(Long activityId, Long userId){
        //활동과 사용자가 존재하는지 확인
        JoinActivity joinActivity = joinActivityRepository.findByActivityIdAndUserId(activityId,userId)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 신청입니다."));

        //상태 확인
        if(!joinActivity.getIsAccepted().equals(JoinActivity.JoinStatus.pending)){
            throw new IllegalArgumentException("해당 신청은 이미 처리되었습니다.");

        }

        //상태 변경
        joinActivity.setIsAccepted(JoinActivity.JoinStatus.rejected);
        joinActivityRepository.save(joinActivity);
    }

    /*
    \ 활동 탈퇴
     */
    @Transactional
    public void leaveActivity(Long activityId, Long userId){
        //활동이 존재하는지, 사용자가 존재하는지
         joinActivityRepository.findByActivityIdAndUserId(activityId,userId)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 수 없습니다."));

        //활동 탈퇴
        participateRepository.deleteByActivityIdAndUserId(activityId, userId);



    }



}
