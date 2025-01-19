package com.greenity.server.badge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenity.server.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BadgeType badge_type;

    private LocalDate badge_date;

    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonIgnore
    private User owner;

    public enum BadgeType {
        FIRST, LEADER ,ENERGY, RECYCLE, PLOGGING, ECHO, ZEROWASTE, ZEROCABON, COMMUNICATION
    }
}