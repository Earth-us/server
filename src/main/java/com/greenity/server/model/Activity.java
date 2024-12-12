package com.greenity.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Schema(description = "활동 엔티티")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(length = 20)
    private String location;

    @Column(nullable = false)
    private java.time.LocalDateTime subEnd;

    @Column(nullable = false)
    private Long currentParticipants = 0L; //현재 참여 인원
    @Column(nullable = false)
    private Long recruitNum; //최대 참여 인원

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityMethod activityMethod;

    @Column
    private Boolean isApprovalRequired;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private Long activityCount;

    private String activityPic;

    @Column(nullable = false)
    private Boolean isEnd;

    private Long groupId;

    public enum Category {
        ENERGY, RECYCLE, PLOGGING, ECHO, ZEROWASTE, ZEROCARBON
    }

    public enum ActivityMethod {
        ONLINE, OFFLINE
    }
}
