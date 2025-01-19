package com.greenity.server.activity.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@Schema(description = "회차 엔티티")
public class ActivityRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 회차 ID (Primary Key)

    @ManyToOne
    @JoinColumn(name = "activity", nullable = false)
    private Activity activity; // 연관된 활동 (Foreign Key)

    @Column(nullable = false)
    private LocalDate activityDate; // 회차 날짜

    @Column(nullable = true)
    private LocalTime activityTime;

    @Column(nullable = false)
    private Long activityNum; // 활동별 회차 번호

    @Column(length = 50)
    private String location; // 회차 장소
}
