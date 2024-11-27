package com.greenity.server.controller;

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
    public ResponseEntity<List<Activity>> searchActivities(
            @RequestParam(required = false) Boolean isEnd,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String activityMethod
    ) {
        // Enum 변환
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
            return ResponseEntity.badRequest().body(null);  // 잘못된 값일 경우 400 에러 반환
        }

        // Service 호출
        List<Activity> activities = activityService.searchActivities(isEnd, categoryEnum, methodEnum);
        return ResponseEntity.ok(activities);
    }


}
