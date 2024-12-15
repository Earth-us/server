package com.greenity.server.controller;

import com.greenity.server.dto.ApplicationDTO;
import com.greenity.server.repository.UserRepository;
import com.greenity.server.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@Tag(name = "User Application API", description = "유저 API")
public class UserController {
    private final ApplicationService applicationService;

    public UserController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //사용자의 신청 내역 조회
    @GetMapping("/{userId}/applications")
    @Operation(summary = "신청한 활동 리스트 조회", description = "사용자")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByUser(
            @PathVariable Long userId){
        List<ApplicationDTO> applications = applicationService.getApplicationsByUserId(userId);
        return ResponseEntity.ok(applications);
    }

}
