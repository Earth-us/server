package com.greenity.server.user.entity;

import com.greenity.server.badge.model.Badge;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String nickname;

    private String email;

    @Column(name="local_pw")
    private String password;

    @Column(name="activity_count")
    private Long activityCount;

    @Column(name="login_type")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    public enum LoginType{
        LOCAL,
        KAKAO
    }

    @Column(name="kakao_id")
    private Long kakaoId;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;  // 기본값 USER

    public enum Role {
        USER,
        ADMIN
    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Badge> badges;

    @Builder
     public User(String name, String phone, String nickname, LoginType loginType, String email, String password, Long activityCount, Long kakaoId, Role role) {
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.loginType = loginType;
        this.email = email;
        this.password = password;
        this.activityCount = activityCount;
        this.kakaoId = kakaoId;
        this.role = role;
    }

    public void updateUser(String nickname, String phone) {
        this.nickname = nickname;
        this.phone = phone;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
