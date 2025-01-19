package com.greenity.server.activity.mapper;

import com.greenity.server.activity.dto.ActivityDTO;
import com.greenity.server.activity.entity.Activity;

public class ActivityMapper {

    // Entity -> DTO 변환
    public static ActivityDTO toDTO(Activity activity) {
        return new ActivityDTO(
                activity.getTitle(),
                activity.getLocation(),
                activity.getSubEnd(),
                activity.getRecruitNum(),
                activity.getCurrentParticipants(),
                activity.getCategory(),
                activity.getActivityMethod(),
                activity.getIsApprovalRequired(),
                activity.getContent(),
                activity.getActivityCount(),
                activity.getActivityPic(),
                activity.getIsEnd(),
                activity.getGroupId()
        );
    }

    // DTO -> Entity 변환
    public static Activity toEntity(ActivityDTO dto) {
        Activity activity = new Activity();
        activity.setTitle(dto.getTitle());
        activity.setLocation(dto.getLocation());
        activity.setSubEnd(dto.getSubEnd());
        activity.setRecruitNum(dto.getRecruitNum());
        activity.setCurrentParticipants(dto.getCurrentParticipants());
        activity.setCategory(dto.getCategory());
        activity.setActivityMethod(dto.getActivityMethod());
        activity.setIsApprovalRequired(dto.getIsApprovalRequired());
        activity.setContent(dto.getContent());
        activity.setActivityCount(dto.getActivityCount());
        activity.setActivityPic(dto.getActivityPic());
        activity.setIsEnd(dto.getIsEnd());
        activity.setGroupId(dto.getGroupId());
        return activity;
    }

    public static void updateEntity(Activity activity, ActivityDTO dto) {
        if (dto.getTitle() != null) activity.setTitle(dto.getTitle());
        if (dto.getLocation() != null) activity.setLocation(dto.getLocation());
        if (dto.getSubEnd() != null) activity.setSubEnd(dto.getSubEnd());
        if (dto.getRecruitNum() != null) activity.setRecruitNum(dto.getRecruitNum());
        if(dto.getCurrentParticipants() != null) activity.setCurrentParticipants(dto.getCurrentParticipants());
        if (dto.getCategory() != null) activity.setCategory(dto.getCategory());
        if (dto.getActivityMethod() != null) activity.setActivityMethod(dto.getActivityMethod());
        if (dto.getIsApprovalRequired() != null) activity.setIsApprovalRequired(dto.getIsApprovalRequired());
        if (dto.getContent() != null) activity.setContent(dto.getContent());
        if (dto.getActivityCount() != null) activity.setActivityCount(dto.getActivityCount());
        if (dto.getActivityPic() != null) activity.setActivityPic(dto.getActivityPic());
        if (dto.getIsEnd() != null) activity.setIsEnd(dto.getIsEnd());
        if (dto.getGroupId() != null) activity.setGroupId(dto.getGroupId());
    }

}
