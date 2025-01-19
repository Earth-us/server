package com.greenity.server.user.service;

import com.greenity.server.user.dto.request.ResetPasswordRequest;
import com.greenity.server.user.dto.request.VerifyCodeRequest;
import com.greenity.server.global.util.email.EmailService;
import com.greenity.server.global.util.redis.RedisService;
import com.greenity.server.user.entity.User;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RedisService redisService;

    private final PasswordEncoder passwordEncoder;

    public void sendAuthCode(String email) {

        User user = userRepository.findByEmail((email))
                .orElseThrow(() -> new RuntimeException("등록된 계정이 아닙니다."));

        if(user.getLoginType().equals(User.LoginType.KAKAO)){
            throw new RuntimeException("카카오 로그인 사용자는 카카오 로그인을 이용");
        }

        emailService.sendMail(email);
    }

    public void verifyAuthCode(VerifyCodeRequest verificationRequest) {

        String savedAuthCode = redisService.getAuthCode(verificationRequest.getEmail());

        if(!savedAuthCode.equals(verificationRequest.getCode())) {
            throw new RuntimeException("인증 코드가 알맞지 않습니다");
        }
    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {

        User user = userRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않음"));

        user.updatePassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);
    }
}
