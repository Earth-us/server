package com.greenity.server.club.entity;

import com.greenity.server.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "join_group") // 테이블 이름 설정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false) // 외래 키 group_id
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 user_id
    private User user;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted;
}
