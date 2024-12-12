package com.greenity.server.controller;


import com.greenity.server.dto.ActivityDTO;
import com.greenity.server.dto.ActivitySearchDTO;
import com.greenity.server.model.Activity;
import com.greenity.server.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "활동 생성", description = "활동을 생성합니다.")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {

        ActivityDTO createdActivity = activityService.createActivity(activityDTO);
        return ResponseEntity.ok(createdActivity);
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

        if (category != null) {
            categoryEnum = Activity.Category.valueOf(category.toUpperCase());
        }
        if (activityMethod != null) {
            methodEnum = Activity.ActivityMethod.valueOf(activityMethod.toUpperCase());
        }

        List<ActivitySearchDTO> activities = activityService.searchActivities(isEnd, categoryEnum, methodEnum);
        return ResponseEntity.ok(activities);
    }


    // Activity 상세 조회
    @GetMapping("/{activityId}/detail")
    @Operation(summary = "활동 상세 조회", description = "ID에 해당하는 활동의 상세 정보를 조회합니다.")
    public ResponseEntity<ActivityDTO> getActivityDetail(@PathVariable Long activityId) {

        // Service 계층에서 ActivityDTO 조회
        ActivityDTO activityDTO = activityService.getActivityById(activityId);
        return ResponseEntity.ok(activityDTO);

    }


    //활동 수정
    @PutMapping("/{id}")
    @Operation(summary = "활동 수정", description="특정 활동의 정보를 수정합니다.")
    public ResponseEntity<ActivityDTO> updateActivity(
        @PathVariable Long id,
        @RequestBody ActivityDTO activityDTO){

        ActivityDTO updateActivity = activityService.updateActivity(id,activityDTO);
        return ResponseEntity.ok(updateActivity);

    }

    //활동 삭제
    @DeleteMapping("/{id}")
    @Operation(summary ="활동 삭제", description = "특정 활동을 삭제합니다.")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id){

        activityService.deleteActivity(id);
        return ResponseEntity.ok("활동이 성공적으로 삭제되었습니다.");

    }

}
