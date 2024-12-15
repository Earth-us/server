package com.greenity.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private long activityId;
    private String activityTitle;
    private String isAccepted;
}
