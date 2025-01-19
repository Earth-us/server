package com.greenity.server.activity.mapper;

import com.greenity.server.activity.dto.ApplicationDTO;
import com.greenity.server.activity.entity.JoinActivity;

public class JoinActivityMapper {
    //joinActivity ->ApplicationDTO 변환
    public static ApplicationDTO toDTO(JoinActivity joinActivity) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setActivityId(joinActivity.getActivity().getId());
        dto.setActivityTitle(joinActivity.getActivity().getTitle());
        dto.setIsAccepted(joinActivity.getIsAccepted().toString());

        return dto;
    }
}


