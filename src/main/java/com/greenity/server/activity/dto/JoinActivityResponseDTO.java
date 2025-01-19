package com.greenity.server.activity.dto;


import com.greenity.server.activity.entity.JoinActivity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinActivityResponseDTO {

    private long userId;  //신청자 id
    private String userName; //신청자 이름
    private JoinActivity.JoinStatus status; //신청 상태


}
