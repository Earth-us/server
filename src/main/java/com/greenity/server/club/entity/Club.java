package com.greenity.server.club.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="club")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String location;

    @Column(name= "sub_end", nullable = false)
    private LocalDateTime subEnd;

    @Column(name="recruit_num", nullable = false)
    private Long recruitNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_mode", nullable = false, length = 10)
    private ActivityMode activityMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "join_method", nullable = false, length = 10)
    private JoinMethod joinMethod;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(name = "activity_count", nullable = false)
    private Long activityCount;

    @Column(name = "activity_pic", length = 255)
    private String activityPic;


}
