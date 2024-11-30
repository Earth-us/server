package com.greenity.server.service;

import com.greenity.server.dto.ActivityDTO;
import com.greenity.server.dto.ActivitySearchDTO;
import com.greenity.server.mapper.ActivityMapper;
import com.greenity.server.model.Activity;
import com.greenity.server.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // 활동 생성
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        // DTO -> Entity 변환
        Activity activity = ActivityMapper.toEntity(activityDTO);

        // 저장
        activity = activityRepository.save(activity);

        // Entity -> DTO 변환 후 반환
        return ActivityMapper.toDTO(activity);
    }

    // 활동 검색
    public List<ActivitySearchDTO> searchActivities(Boolean isEnd, Activity.Category category, Activity.ActivityMethod activityMethod) {
        return activityRepository.searchActivities(isEnd, category, activityMethod);
    }

    // 활동 상세 조회
    public ActivityDTO getActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));
        return ActivityMapper.toDTO(activity);
    }


    // 활동 수정
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        // 활동이 존재하는지 확인
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // Entity 업데이트
        ActivityMapper.updateEntity(activity, activityDTO);

        // 저장
        activity = activityRepository.save(activity);

        // Entity -> DTO 변환 후 반환
        return ActivityMapper.toDTO(activity);
    }


    // 활동 삭제
    @Transactional
    public void deleteActivity(Long activityId) {
        // 활동 조회
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 활동이 존재하지 않습니다."));

        // 활동 삭제
        activityRepository.delete(activity);
    }
}
