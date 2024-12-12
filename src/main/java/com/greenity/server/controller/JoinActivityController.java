package com.greenity.server.controller;


import com.greenity.server.model.JoinActivity;
import com.greenity.server.service.JoinActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity/{activityId}/join")
@Tag(name = "Activity Join API", description = "활동 참여 API")
public class JoinActivityController {

    private final JoinActivityService joinActivityService;

    public JoinActivityController(JoinActivityService joinActivityService) {
        this.joinActivityService = joinActivityService;
    }

    // 1. 활동 신청 생성
    @PostMapping
    @Operation(summary ="활동 신청", description = "특정 활동을 신청합니다.")
    public ResponseEntity<String> joinActivity(
            @PathVariable Long activityId,
            @RequestParam Long userId) {
        String status = joinActivityService.createJoinActivity(activityId, userId);
        return ResponseEntity.ok(status);
    }
/*
    // 2. 특정 상태의 신청 목록 조회
    @GetMapping
    public ResponseEntity<List<JoinActivity>> getJoinActivitiesByStatus(
            @PathVariable Long activityId,
            @RequestParam JoinActivity.JoinStatus status) {
        List<JoinActivity> joinActivities = joinActivityService.getJoinActivitiesByStatus(activityId, status);
        return ResponseEntity.ok(joinActivities);
    }

    // 3. 신청 승인
    @PutMapping("/accept")
    public ResponseEntity<String> acceptJoinActivity(
            @PathVariable Long activityId,
            @RequestParam Long userId) {
        joinActivityService.acceptJoinActivity(activityId, userId);
        return ResponseEntity.ok("신청이 승인되었습니다.");
    }

    // 4. 신청 거절
    @PutMapping("/reject")
    public ResponseEntity<String> rejectJoinActivity(
            @PathVariable Long activityId,
            @RequestParam Long userId) {
        joinActivityService.rejectJoinActivity(activityId, userId);
        return ResponseEntity.ok("신청이 거절되었습니다.");
    }

 */
}
