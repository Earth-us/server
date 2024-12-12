package com.greenity.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JoinActivity {

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
    private JoinStatus isAccepted = JoinStatus.pending; // 신청 상태 (기본값: PENDING)

    public enum JoinStatus {
        pending, accepted, rejected
    }
}
