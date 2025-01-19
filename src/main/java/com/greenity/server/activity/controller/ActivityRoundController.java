package com.greenity.server.activity.controller;

import com.greenity.server.activity.dto.ActivityRoundDTO;
import com.greenity.server.activity.service.ActivityRoundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity/{activityId}/rounds")
@Tag(name = "Activity Round API", description = "활동 회차 관련 API")
public class ActivityRoundController {

    private final ActivityRoundService activityRoundService;

    @Autowired
    public ActivityRoundController(ActivityRoundService activityRoundService) {
        this.activityRoundService = activityRoundService;
    }

    // 회차 생성
    @PostMapping
    @Operation(summary = "활동 회차 생성", description = "특정 활동의 회차를 생성합니다.")
    public ResponseEntity<String> createActivityRound(
        @PathVariable Long activityId,
        @RequestBody ActivityRoundDTO activityRoundDTO) {

        activityRoundService.createActivityRound(activityId, activityRoundDTO);
        return ResponseEntity.ok("회차가 성공적으로 생성되었습니다.");

    }

    // 회차 조회
    @GetMapping
    @Operation(summary = "활동 회차 조회", description = "특정 활동의 모든 회차 정보를 조회합니다.")
    public ResponseEntity<List<ActivityRoundDTO>> getActivityRounds(@PathVariable Long activityId) {

        List<ActivityRoundDTO> rounds = activityRoundService.getActivityRounds(activityId);
        return ResponseEntity.ok(rounds);

    }

    // 회차 수정
    @PutMapping("/{activityNum}")
    @Operation(summary = "활동 회차 수정", description = "특정 활동의 특정 회차 정보를 수정합니다.")
    public ResponseEntity<String> updateActivityRound(
            @PathVariable Long activityId,
            @PathVariable Long activityNum,
            @RequestBody ActivityRoundDTO activityRoundDTO) {

            activityRoundService.updateActivityRound(activityId, activityNum, activityRoundDTO);
            return ResponseEntity.ok("활동 회차가 성공적으로 수정되었습니다.");

    }

    // 회차 삭제
    @DeleteMapping("/{activityNum}")
    @Operation(summary = "활동 회차 삭제", description = "특정 활동의 특정 회차를 삭제합니다.")
    public ResponseEntity<String> deleteActivityRound(
            @PathVariable Long activityId,
            @PathVariable Long activityNum) {

            activityRoundService.deleteActivityRound(activityId, activityNum);
            return ResponseEntity.ok("회차가 성공적으로 삭제되었습니다.");

    }
}
