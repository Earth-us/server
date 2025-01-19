/*
package com.greenity.server.calendar.controller;

import com.greenity.server.calendar.dto.CalendarActivityResponse;
import com.greenity.server.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar/schedule/activity")
    public ResponseEntity<?> getUserScheduleActivity(@AuthenticationPrincipal Long userId) {
        List<CalendarActivityResponse> activities = calendarService.getUserScheduleActivity(userId);

        return ResponseEntity.ok(activities);
    }
}

 */
