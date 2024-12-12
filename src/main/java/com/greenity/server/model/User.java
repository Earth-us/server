package com.greenity.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 ID (Primary Key)

    @Column(nullable = false, length = 20)
    private String name; // 이름

    @Column(nullable = false, length = 20, unique = true)
    private String phone; // 전화번호 (유니크)

    @Column(nullable = false, length = 20, unique = true)
    private String nickname; // 닉네임 (유니크)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType loginType; // 로그인 타입 (local, kakao)

    @Column(nullable = false)
    private String email; // 이메일

    @Column(length = 255)
    private String localPw; // 로컬 비밀번호 (NULL 가능)

    @Column(nullable = false)
    private Long activityCount; // 참여한 활동 수

    public enum LoginType {
        local, kakao
    }
}
