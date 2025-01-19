package com.greenity.server.user.controller;

import com.greenity.server.user.dto.request.SignUpRequest;
import com.greenity.server.user.dto.request.UpdateUserRequest;
import com.greenity.server.user.dto.response.UserResponse;
import com.greenity.server.auth.jwt.CustomUserDetails;
import com.greenity.server.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<?> localSignUp(SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);

        return ResponseEntity.ok("회원가입 되었습니다");
    }

    @Operation(summary = "회원 조회")
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> showUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserResponse user = userService.showUser(userDetails.getUserId());

        return ResponseEntity.ok(user);
    }

    @Operation(summary = "회원 수정")
    @PutMapping("/profile")
    public ResponseEntity<?> updateUser(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            UpdateUserRequest updateUserRequest)
    {
        userService.updateUser(userDetails.getUserId(), updateUserRequest);

        return ResponseEntity.ok("회원 수정 완료되었습니다");
    }

    @Operation(summary = "회원 삭제")
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.deleteUser(userDetails.getUserId());

        return ResponseEntity.ok("정상적으로 탈퇴되었습니다");
    }
}
