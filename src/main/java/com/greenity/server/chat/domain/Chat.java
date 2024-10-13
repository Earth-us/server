package com.greenity.server.chat.domain;

import com.greenity.server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creater", referencedColumnName = "id")
    private User creater;

    @ManyToOne
    @JoinColumn(name = "entrant", referencedColumnName = "id")
    private User entrant;

    @Builder
    public Chat(User creater, User entrant) {
        this.creater = creater;
        this.entrant = entrant;
    }

}
