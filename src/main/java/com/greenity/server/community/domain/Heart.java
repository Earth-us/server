package com.greenity.server.community.domain;

import com.greenity.server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"heart_user", "original_writing"})
})
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "heart_user", referencedColumnName = "id")
    private User heartUser;

    @ManyToOne
    @JoinColumn(name = "original_writing", referencedColumnName = "id")
    private Writing originalWriting;

    @Builder
    public Heart(User heartUser, Writing originalWriting) {
        this.heartUser = heartUser;
        this.originalWriting = originalWriting;
    }

}
