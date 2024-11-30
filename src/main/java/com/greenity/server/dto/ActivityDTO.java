package com.greenity.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greenity.server.model.Activity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
public class ActivityDTO {

    @NotNull
    private String title;

    private String location;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime subEnd;

    private Long recruitNum;

    @NotNull
    private Activity.Category category;

    @NotNull
    private Activity.ActivityMethod activityMethod;

    @NotNull
    private Boolean isApprovalRequired;

    @NotNull
    private String content;

    @NotNull
    private Long activityCount;

    private String activityPic;

    @NotNull
    private Boolean isEnd;

    private Long groupId;


}
