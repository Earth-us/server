package com.greenity.server.activity.mapper;

import com.greenity.server.activity.dto.ActivityRoundDTO;
import com.greenity.server.activity.entity.ActivityRound;

public class ActivityRoundMapper {

    public static ActivityRoundDTO toDTO(ActivityRound activityRound) {
        return new ActivityRoundDTO(
                activityRound.getActivityDate(),
                activityRound.getActivityTime(),
                activityRound.getActivityNum(),
                activityRound.getLocation()
        );
    }

    public static ActivityRound toEntity(ActivityRoundDTO dto) {
        ActivityRound activityRound = new ActivityRound();
        activityRound.setActivityDate(dto.getActivityDate());
        activityRound.setActivityTime(dto.getActivityTime());
        activityRound.setActivityNum(dto.getActivityNum());
        activityRound.setLocation(dto.getLocation());
        return activityRound;
    }

    public static void updateEntity(ActivityRound activityRound, ActivityRoundDTO dto) {
        activityRound.setActivityDate(dto.getActivityDate());
        activityRound.setActivityTime(dto.getActivityTime());
        activityRound.setLocation(dto.getLocation());
        // 회차 번호는 보통 수정하지 않으므로 제외. 필요 시 추가 가능.
    }
}
