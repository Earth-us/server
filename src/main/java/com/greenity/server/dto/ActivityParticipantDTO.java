package com.greenity.server.dto;

import com.greenity.server.model.Participate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityParticipantDTO {

    private Long userId; // 사용자 ID (Primary Key)
    private String nickname; // 닉네임 (유니크)
    private Participate.Role role;// 역할




}
