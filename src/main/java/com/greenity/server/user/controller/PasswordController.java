package com.greenity.server.user.controller;

import com.greenity.server.user.dto.request.ResetPasswordRequest;
import com.greenity.server.user.dto.request.VerifyCodeRequest;
import com.greenity.server.user.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/password")
public class PasswordController {

    private final PasswordService passwordService;

    @Operation(summary = "비밀번호 요청")
    @PostMapping("/request-password")
    public ResponseEntity<?> requestPassword(String email) {
        passwordService.sendAuthCode(email);

        return ResponseEntity.ok("이메일로 인증하세요");
    }

    @Operation(summary = "인증 코드 검증")
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyAuthCode(VerifyCodeRequest verificationRequest) {
        passwordService.verifyAuthCode(verificationRequest);

        return ResponseEntity.ok("인증되었습니다.");
    }

    @Operation(summary = "비밀번호 재설정")
    @PostMapping("/reset")
    public ResponseEntity<?> ResetPassword(ResetPasswordRequest resetPasswordRequest) {
        passwordService.resetPassword(resetPasswordRequest);

        return ResponseEntity.ok("새로운 비밀번호를 설정했습니다.");
    }
}
