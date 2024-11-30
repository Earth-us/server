package com.greenity.server.controller;


import com.greenity.server.dto.ActivityRoundDTO;
import com.greenity.server.dto.ActivitySearchDTO;
import com.greenity.server.model.Activity;
import com.greenity.server.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/activity")
@Tag(name = "Activity API", description = "활동 관련 API")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // 활동 생성
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        try {
            Activity createdActivity = activityService.createActivity(activity);
            return ResponseEntity.ok(createdActivity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 활동 검색
    @GetMapping("/search")
    @Operation(summary = "활동 검색", description = "조건에 따라 활동을 검색합니다.")
    public ResponseEntity<List<ActivitySearchDTO>> searchActivities(
            @RequestParam(required = false) Boolean isEnd,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String activityMethod
    ) {
        Activity.Category categoryEnum = null;
        Activity.ActivityMethod methodEnum = null;

        try {
            if (category != null) {
                categoryEnum = Activity.Category.valueOf(category.toUpperCase());
            }
            if (activityMethod != null) {
                methodEnum = Activity.ActivityMethod.valueOf(activityMethod.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 잘못된 값일 경우 400 반환
        }

        List<ActivitySearchDTO> activities = activityService.searchActivities(isEnd, categoryEnum, methodEnum);
        return ResponseEntity.ok(activities);
    }


    // Activity 상세 조회
    @GetMapping("/{activityId}/detail")
    @Operation(summary = "활동 상세 조회", description = "ID에 해당하는 활동의 상세 정보를 조회합니다.")
    public ResponseEntity<Activity> getActivityDetail(@PathVariable Long activityId) {
        // Service 계층에서 Activity를 조회
        Activity activity = activityService.getActivityById(activityId);
        if (activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 응답
        }
    }

    //활동 수정





    //회차 생성
    @PostMapping("/{activityID}/rounds")
    @Operation(summary = "활동 회차 등록", description = "특정 활동에 새로운 회차를 등록합니다.")
    public ResponseEntity<String> createActivityRound(
            @PathVariable Long activityID,
            @RequestBody ActivityRoundDTO activityRoundDTO) {

        try {
            activityService.createActivityRound(activityID, activityRoundDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("활동 회차가 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러 발생");
        }
    }

    //회차 조회
    @GetMapping("/{activityID}/rounds")
    @Operation(summary = "활동 회차 조회", description = "특정 활동의 모든 회차 정보를 조회합니다.")
    public ResponseEntity<?> getActivityRounds(@PathVariable Long activityID) { // ResponseEntity<?>로 변경
        try {
            List<ActivityRoundDTO> rounds = activityService.getActivityRounds(activityID);
            return ResponseEntity.ok(rounds);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 활동입니다."); // 사용자 친화적인 메시지 반환
        }
    }

    //회차 수정
    @PutMapping("/{activityID}/rounds/{activityNum}")
    @Operation(summary = "활동 회차 수정", description = "특정 활동의 특정 회차 정보를 수정합니다.")
    public ResponseEntity<String> updateActivityRound(
            @PathVariable Long activityID,
            @PathVariable Long activityNum,
            @RequestBody ActivityRoundDTO activityRoundDTO) {

        try {
            activityService.updateActivityRound(activityID, activityNum, activityRoundDTO);
            return ResponseEntity.ok("활동 회차가 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러 발생");
        }
    }

    //회차 삭제
    @DeleteMapping("/{activityID}/rounds/{activityNum}")
    @Operation(summary = "활동 회차 삭제", description = "특정 활동의 특정 회차를 삭제")
    public ResponseEntity<String> deleteActivityRound(
            @PathVariable Long activityID,
            @PathVariable Long activityNum){
        try{
            activityService.deleteActivityRound(activityID, activityNum);
            return ResponseEntity.ok("활동 회차가 성공적으로 삭제되었습니다.");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }




}
