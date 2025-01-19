package com.greenity.server.activity.dto;

import com.greenity.server.activity.entity.Activity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ActivitySearchDTO {
    private String title;
    private String location;
    private LocalDateTime subEnd;
    private Activity.Category category;
    private Activity.ActivityMethod activityMethod;
    private String activityPic;
    private Boolean isEnd;

    public ActivitySearchDTO(String title, String location, LocalDateTime subEnd, Activity.Category category,
                             Activity.ActivityMethod activityMethod, String activityPic, Boolean isEnd) {
        this.title = title;
        this.location = location;
        this.subEnd = subEnd;
        this.category = category;
        this.activityMethod = activityMethod;
        this.activityPic = activityPic;
        this.isEnd = isEnd;
    }


}
