package com.greenity.server.community.domain;

import com.greenity.server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", length = 100, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "commenter", referencedColumnName = "id")
    private User commenter;

    @ManyToOne
    @JoinColumn(name = "original_writing", referencedColumnName = "id")
    private Writing originalWriting;

    @Builder
    public Comment(String content, User commenter, Writing originalWriting) {
        this.content = content;
        this.commenter = commenter;
        this.originalWriting = originalWriting;
    }

}
