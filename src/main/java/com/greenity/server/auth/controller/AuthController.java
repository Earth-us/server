package com.greenity.server.auth.controller;

import com.greenity.server.auth.jwt.CustomUserDetails;
import com.greenity.server.auth.dto.LoginRequest;
import com.greenity.server.user.dto.response.KakaoUserResponse;
import com.greenity.server.auth.service.KakaoService;
import com.greenity.server.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${kakao.api-key}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    private final AuthService authService;
    private final KakaoService kakaoService;

    @Operation(summary = "카카오 로그인")
    @GetMapping("/kakao")
    public void kakaoLogin(HttpServletResponse response) throws IOException {
        String url = "https://kauth.kakao.com/oauth/authorize?" +
                "client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code";

        response.sendRedirect(url);
    }

    @Operation(summary = "카카오 로그인 콜백")
    @GetMapping("/login/kakao/callback")
    public ResponseEntity<?> kakaoLoginCallback(@RequestParam("code") String code, HttpServletResponse response) throws Exception {

        String accessToken = kakaoService.getKakaoAccessToken(code);
        KakaoUserResponse kakaoUserResponse = kakaoService.getUserInfo(accessToken);

        String token = authService.kakaoLogin(kakaoUserResponse, response);

        return ResponseEntity.ok(" 카카오 로그인 성공 : " + token);
    }

    @Operation(summary = "로컬 로그인")
    @PostMapping("/login/local")
    public ResponseEntity<?> localLogin(LoginRequest loginRequest, HttpServletResponse response) {
        String token = authService.localLogin(loginRequest, response);

        return ResponseEntity.ok("로컬 로그인 성공 : " + token);
    }

    @Operation(summary = "토큰 재발급")
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = authService.refreshAccessToken(request);

        return ResponseEntity.ok("토큰 재발급 성공 : " + token);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal CustomUserDetails userDetails, HttpServletResponse response) {
        authService.logout(userDetails.getUserId(), response);

        return ResponseEntity.ok("로그아웃에 성공했습니다");
    }
}
