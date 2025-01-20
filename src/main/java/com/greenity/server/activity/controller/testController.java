package com.greenity.server.activity.controller;

import com.greenity.server.activity.service.JoinActivityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity/{activityId}/join")
@Tag(name = "Activity Join API", description = "활동 참여 API")
public class testController {

    private final JoinActivityService joinActivityService;

    public testController(JoinActivityService joinActivityService) {
        this.joinActivityService = joinActivityService;
    }
}
