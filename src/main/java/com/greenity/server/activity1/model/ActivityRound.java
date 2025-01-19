package com.greenity.server.activity1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ActivityRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_date")
    private LocalDate activityDate;

    @Column(name = "activity_time")
    private String activityTime;

    @Column(name = "activity_num")
    private int activityNum;

    private String location;

    @ManyToOne
    @JoinColumn(name = "activity")
    @JsonIgnore
    private Activity activity;
}
