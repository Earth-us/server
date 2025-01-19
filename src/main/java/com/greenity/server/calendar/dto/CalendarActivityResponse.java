package com.greenity.server.calendar.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CalendarActivityResponse {

    private String title;
    private LocalDate activityDate;
    private String activityTime;
    private int activityNum;
}
