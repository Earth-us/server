package com.greenity.server.dto;


import com.greenity.server.model.JoinActivity;
import com.greenity.server.model.Participate;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinActivityResponseDTO {

    private long userId;  //신청자 id
    private String userName; //신청자 이름
    private JoinActivity.JoinStatus status; //신청 상태


}
