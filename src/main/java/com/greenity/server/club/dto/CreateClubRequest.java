package com.greenity.server.club.dto;

import com.greenity.server.club.entity.ActivityMode;
import com.greenity.server.club.entity.Club;
import com.greenity.server.club.entity.JoinMethod;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateClubRequest {

    @NotNull(message = "이름은 필수입니다.")
    @Size(min = 2, max = 20, message = "이름은 2자 이상, 20자 이하여야 합니다.")
    private String name;

    @NotNull(message = "위치는 필수입니다.")
    @Size(min = 1, max = 20, message = "위치는 1자 이상, 20자 이하여야 합니다.")
    private String location;

    @NotNull(message = "종료일은 필수입니다.")
    private LocalDateTime subEnd;

    @NotNull(message = "모집 인원은 필수입니다.")
    @Min(value = 1, message = "모집 인원은 최소 1명 이상이어야 합니다.") // 최소값 설정
    @Max(value = 1000, message = "모집 인원은 최대 1000명 이하여야 합니다.") // 최대값 설정
    private Long recruitNum;

    @NotNull(message = "활동 모드는 필수입니다.")
    private ActivityMode activityMode;

    @NotNull(message = "가입 방식은 필수입니다.")
    private JoinMethod joinMethod;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(min = 10, max = 200, message = "내용은 10자 이상, 200자 이하여야 합니다.")
    private String content;

    @Size(max = 255, message = "활동 이미지는 최대 255자까지 가능합니다.")
    private String activityPic;


    public Club toEntity() {
        return Club.builder()
                .name(this.name)
                .location(this.location)
                .subEnd(this.subEnd)
                .recruitNum(this.recruitNum)
                .activityMode(this.activityMode)
                .joinMethod(this.joinMethod)
                .content(this.content)
                .activityCount(0L)
                .activityPic(this.activityPic)
                .build();
    }

}