package com.greenity.server.activity.entity;

import com.greenity.server.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @ManyToOne
    @JoinColumn(name = "activity", nullable = false)
    private Activity activity; // 활동 ID (외래 키)

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user; // 사용자 ID (외래 키)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 역할 (리더/회원)

    public enum Role {
        leader, member
    }
}
