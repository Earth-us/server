package com.greenity.server.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class ActivityRoundDTO {
    private LocalDate activityDate; // 날짜
    @JsonFormat(pattern = "HH:mm") // 시:분만 처리
    @Schema(type = "string", example = "14:30")
    private LocalTime activityTime; // 시/분만 입력
    private Long activityNum;       // 회차 번호
    private String location;        // 장소

}
