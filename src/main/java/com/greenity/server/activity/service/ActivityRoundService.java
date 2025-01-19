package com.greenity.server.activity.service;

import com.greenity.server.activity.dto.ActivityRoundDTO;
import com.greenity.server.activity.entity.Activity;
import com.greenity.server.activity.entity.ActivityRound;
import com.greenity.server.activity.mapper.ActivityRoundMapper;
import com.greenity.server.activity.repository.ActivityRepository;
import com.greenity.server.activity.repository.ActivityRoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityRoundService {

    private final ActivityRepository activityRepository;
    private final ActivityRoundRepository activityRoundRepository;

    public ActivityRoundService(ActivityRepository activityRepository, ActivityRoundRepository activityRoundRepository) {
        this.activityRepository = activityRepository;
        this.activityRoundRepository = activityRoundRepository;
    }

    // 회차 생성
    public void createActivityRound(Long activityId, ActivityRoundDTO activityRoundDTO) {
        // 활동 조회
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // 요청된 회차 번호 검증 (번호의 존재 여부 확인)
        long requestedActivityNum = activityRoundDTO.getActivityNum();
        if (requestedActivityNum <= 0 || requestedActivityNum > activity.getActivityCount()) {
            throw new IllegalArgumentException("잘못된 회차번호입니다.");
        }
        if (activityRoundRepository.existsByActivityIdAndActivityNum(activityId, requestedActivityNum)) {
            throw new IllegalArgumentException("회차번호 " + requestedActivityNum + "는 이미 생성되었습니다.");
        }

        // DTO -> Entity 변환
        ActivityRound activityRound = ActivityRoundMapper.toEntity(activityRoundDTO);
        activityRound.setActivity(activity);

        // 저장
        activityRoundRepository.save(activityRound);
    }


    // 회차 조회
    public List<ActivityRoundDTO> getActivityRounds(Long activityId) {
        // 활동 존재 여부 확인
        activityRepository.findById(activityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // 활동의 모든 회차 조회 및 Entity -> DTO 변환
        return activityRoundRepository.findByActivityId(activityId).stream()
                .map(ActivityRoundMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 회차 수정
    public void updateActivityRound(Long activityId, long activityNum, ActivityRoundDTO activityRoundDTO) {
        // 활동 존재 여부 확인 (데이터 일관성을 위한 검증)
        if (!activityRepository.existsById(activityId)) {
            throw new IllegalArgumentException("해당 활동이 존재하지 않습니다.");
        }

        // 수정할 회차 조회
        ActivityRound activityRound = activityRoundRepository.findByActivityIdAndActivityNum(activityId, activityNum)
                .orElseThrow(() -> new IllegalArgumentException("회차번호 " + activityNum + "는 존재하지 않습니다."));

        // DTO -> Entity 데이터 수정
        ActivityRoundMapper.updateEntity(activityRound, activityRoundDTO);

        // 저장
        activityRoundRepository.save(activityRound);
    }


    // 회차 삭제
    public void deleteActivityRound(Long activityId, long activityNum) {
        // 삭제할 회차 조회
        ActivityRound activityRound = activityRoundRepository.findByActivityIdAndActivityNum(activityId, activityNum)
                .orElseThrow(() -> new IllegalArgumentException("회차번호 " + activityNum + "는 존재하지 않습니다."));

        // 삭제
        activityRoundRepository.delete(activityRound);
    }
}
