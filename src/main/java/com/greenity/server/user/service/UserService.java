package com.greenity.server.user.service;

import com.greenity.server.user.dto.request.SignUpRequest;
import com.greenity.server.user.model.User;
import com.greenity.server.user.dto.request.UpdateUserRequest;
import com.greenity.server.user.dto.response.UserResponse;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .name(signUpRequest.getName())
                .phone(signUpRequest.getPhone())
                .nickname(signUpRequest.getNickname())
                .loginType(User.LoginType.LOCAL)
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .activityCount(0L)
                .role(User.Role.USER)
                .build();

        userRepository.save(user);
    }

    public UserResponse showUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("등록된 사용자가 아닙니다."));

        return UserResponse.fromEntity(user);
    }

    public void updateUser(Long userId, UpdateUserRequest updateUserRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("등록된 사용자가 아닙니다."));

        user.updateUser(updateUserRequest.getNickname(), updateUserRequest.getPhone());

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("등록된 사용자가 아닙니다."));

        userRepository.deleteById(user.getId());
    }

    /*

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            throw new IllegalStateException("인증된 사용자가 없습니다.");
        }

        String nickname = authentication.getName();

        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

     */
}