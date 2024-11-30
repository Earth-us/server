package com.greenity.server.service;


import com.greenity.server.dto.ActivityRoundDTO;
import com.greenity.server.dto.ActivitySearchDTO;
import com.greenity.server.model.Activity;
import com.greenity.server.model.ActivityRound;
import com.greenity.server.repository.ActivityRepository;
import com.greenity.server.repository.ActivityRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityRoundRepository activityRoundRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, ActivityRoundRepository activityRoundRepository) {
        this.activityRepository = activityRepository;
        this.activityRoundRepository = activityRoundRepository;
    }


    //활동 생성
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    //활동검색
    public List<ActivitySearchDTO> searchActivities(Boolean isEnd, Activity.Category category, Activity.ActivityMethod activityMethod) {
        return activityRepository.searchActivities(isEnd, category, activityMethod);
    }

    //활동 상세 조회( ID로 Activity 조회)
    public Activity getActivityById(Long activityId) {
        return activityRepository.findById(activityId).orElse(null);
    }

    //회차 생성
    public void createActivityRound(Long activityID, ActivityRoundDTO activityRoundDTO) {
        // 활동 존재 여부 확인
        Activity activity = activityRepository.findById(activityID)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // DTO -> Entity 변환
        ActivityRound activityRound = new ActivityRound();
        activityRound.setActivity(activity);
        activityRound.setActivityDate(activityRoundDTO.getActivityDate());
        activityRound.setActivityTime(activityRoundDTO.getActivityTime()); // 시/분만 처리
        activityRound.setActivityNum(activityRoundDTO.getActivityNum());
        activityRound.setLocation(activityRoundDTO.getLocation());

        // 저장
        activityRoundRepository.save(activityRound);
    }

    //회차 조회
    public List<ActivityRoundDTO> getActivityRounds(Long activityID) {
        // 활동 존재 여부 확인
        Activity activity = activityRepository.findById(activityID)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // 활동의 모든 회차 조회
        List<ActivityRound> rounds = activityRoundRepository.findByActivityId(activityID);

        // Entity -> DTO 변환
        return rounds.stream()
                .map(round -> new ActivityRoundDTO(
                        round.getActivityDate(),   // 날짜
                        round.getActivityTime(),   // 시간
                        round.getActivityNum(),    // 회차 번호
                        round.getLocation()        // 장소
                ))
                .collect(Collectors.toList());
    }

    //회차 수정
    public void updateActivityRound(Long activityID, Long activityNum, ActivityRoundDTO activityRoundDTO) {
        // 활동 존재 여부 확인
        Activity activity = activityRepository.findById(activityID)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // 회차 존재 여부 확인
        ActivityRound activityRound = activityRoundRepository.findByActivityIdAndActivityNum(activityID, activityNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동에 해당 회차가 존재하지 않습니다."));

        // 수정할 데이터 설정
        activityRound.setActivityDate(activityRoundDTO.getActivityDate());
        activityRound.setActivityTime(activityRoundDTO.getActivityTime());
        activityRound.setLocation(activityRoundDTO.getLocation());

        // 저장
        activityRoundRepository.save(activityRound);
    }

    //회차 삭제
    public void deleteActivityRound(Long activityID, Long activityNum){
        //활동이 존재하는지 확인
        Activity activity = activityRepository.findById(activityID)
                .orElseThrow(()->new IllegalArgumentException("해당 활동이 존재하지 않습니다."));
        //삭제 대상 회차 찾기
        ActivityRound activityRound = activityRoundRepository.findByActivityIdAndActivityNum(activityID, activityNum)
                .orElseThrow(()->new IllegalArgumentException("해당 활동에 해당 회차가 존재하지 않습니다."));
        //삭제
        activityRoundRepository.delete(activityRound);
    }






//    //회차 조회
//    public List<ActivityRoundDTO> getActivityRounds(Long activityID) {
//        // 1. 활동 존재 여부 확인
//        Activity activity = activityRepository.findById(activityID)
//                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));
//
//        // 2. 해당 활동의 회차 조회
//        List<ActivityRound> rounds = activityRoundRepository.findByActivityId(activityID);
//
//        // 3. 엔티티 -> DTO 변환
//        return rounds.stream()
//                .map(round -> new ActivityRoundDTO(
//                        round.getActivityDate(),
//                        round.getActivityNum(),
//                        round.getLocation()))
//                .collect(Collectors.toList());
//    }
//
//    public void updateActivityRound(Long activityID, Long activityNum, ActivityRoundDTO activityRoundDTO) {
//        // 1. 활동 존재 여부 확인
//        Activity activity = activityRepository.findById(activityID)
//                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));
//
//        // 2. 활동에 해당 회차가 있는지 확인
//        ActivityRound activityRound = activityRoundRepository.findByActivityIdAndActivityNum(activityID, activityNum)
//                .orElseThrow(() -> new IllegalArgumentException("해당 활동에 회차가 존재하지 않습니다."));
//
//        // 3. 데이터 수정
//        activityRound.setActivityDate(activityRoundDTO.getActivityDate());
//        activityRound.setLocation(activityRoundDTO.getLocation());
//
//        // 4. 저장
//        activityRoundRepository.save(activityRound);
//    }



}