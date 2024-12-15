package com.greenity.server.mapper;

import com.greenity.server.dto.ApplicationDTO;
import com.greenity.server.model.JoinActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


