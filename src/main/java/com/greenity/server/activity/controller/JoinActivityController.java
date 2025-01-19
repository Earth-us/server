package com.greenity.server.activity.controller;


import com.greenity.server.activity.dto.JoinActivityResponseDTO;
import com.greenity.server.activity.service.JoinActivityService;
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

    // 활동 신청 생성
    @PostMapping
    @Operation(summary ="활동 신청", description = "특정 활동을 신청합니다.")
    public ResponseEntity<String> joinActivity(
            @PathVariable Long activityId,
            @RequestParam Long userId) {
        String status = joinActivityService.createJoinActivity(activityId, userId);
        return ResponseEntity.ok(status);
    }


    //리더가 신청 리스트 확인
    @GetMapping("/pending")
    @Operation(summary ="활동 신청 리스트 확인 (리더)", description = "특정 활동을 신청 리스트를 확인합니다.")
    public ResponseEntity<List<JoinActivityResponseDTO>> getPendingList(
            @PathVariable Long activityId,
            @RequestParam Long userId) {
        List<JoinActivityResponseDTO> result = joinActivityService.getPending(activityId, userId);

        return ResponseEntity.ok(result);
    }

    //리더가 수락 또는 거절

    @PutMapping("/{userId}/accept")
    @Operation(summary =" 활동 승인 (리더)", description = "특정 활동의 신청을 승인합니다.")
    public ResponseEntity<String> acceptActivity(
            @PathVariable Long activityId,
            @PathVariable Long userId) {
        joinActivityService.acceptJoinRequest(activityId, userId);
        return ResponseEntity.ok("사용자의 신청이 승인되었습니다.");
    }

    @PutMapping("/{userId}/reject")
    @Operation(summary ="활동 거절(리더)", description = "특정 활동의 신청을 거절합니다.")
    public ResponseEntity<String> rejectActivity(
            @PathVariable Long activityId,
            @PathVariable Long userId){
        joinActivityService.rejectJoinRequest(activityId, userId);
        return ResponseEntity.ok("사용자의 신청이 거절되었습니다.");
    }


    @DeleteMapping
    @Operation(summary = "활동 탈퇴", description = "특정 활동을 탈퇴합니다.")
    public ResponseEntity<Void> deleteActivity(
            @PathVariable Long activityId,
            @RequestParam Long userId){
        joinActivityService.leaveActivity(activityId, userId);
        return ResponseEntity.noContent().build();
    }


}
