package com.greenity.server.auth.service;

import com.greenity.server.global.util.email.EmailService;
import com.greenity.server.global.util.redis.RedisService;
import com.greenity.server.user.dto.response.KakaoUserResponse;
import com.greenity.server.user.entity.User;
import com.greenity.server.auth.dto.LoginRequest;
import com.greenity.server.auth.jwt.JwtProvider;
import com.greenity.server.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshTokenExpTime;

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;
    private final RedisService redisService;

    public String localLogin(LoginRequest loginRequest, HttpServletResponse response) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("일치하는 이메일이 없습니다"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        return createToken(user, response);
    }

    public String kakaoLogin(KakaoUserResponse kakaoUserResponse, HttpServletResponse response) {
        if (userRepository.findByEmail(kakaoUserResponse.getEmail()).isEmpty()) {
            User user = User.builder()
                    .name(kakaoUserResponse.getNickname())
                    .email(kakaoUserResponse.getEmail())
                    .kakaoId(kakaoUserResponse.getId())
                    .loginType(User.LoginType.KAKAO)
                    .activityCount(0L)
                    .build();

            userRepository.save(user);
        }

        User user = userRepository.findByEmail(kakaoUserResponse.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 아닙니다"));

        return createToken(user, response);
    }

    public String createToken(User user, HttpServletResponse response) {
        String accessToken = jwtProvider.generateAccessToken(user.getId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId());

        ResponseCookie cookie = ResponseCookie
                .from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .domain("localhost")
                .maxAge(refreshTokenExpTime)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return accessToken;
    }

    public String refreshAccessToken(HttpServletRequest request) {
        String refreshToken = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    System.out.println(refreshToken);
                }
            }
        }

        if (refreshToken == null && !jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("토큰이 유효하지 않습니다");
        }

        String userId = jwtProvider.getUserIdFromToken(refreshToken);

        if (!refreshToken.equals(redisService.getRefreshToken(userId))) {
            throw new RuntimeException("토큰이 일치하지 않습니다");
        }

        String newAccessToken = jwtProvider.generateAccessToken(Long.parseLong(userId));
        String newRefreshToken = jwtProvider.generateRefreshToken(Long.parseLong(userId));

        redisService.saveRefreshToken(userId, newRefreshToken, refreshTokenExpTime);

        return newAccessToken;
    }

    public void logout(Long userId, HttpServletResponse response) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다"));

        redisService.deleteRefreshToken(String.valueOf(user.getId()));

        ResponseCookie cookie = ResponseCookie
                .from("refreshToken", null)
                .httpOnly(true)
                .path("/")
                .domain("localhost")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
