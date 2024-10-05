package com.greenity.server.community.domain;

import com.greenity.server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Writing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @Column(name = "writing_pic")
    private String writing_pic;

    @ManyToOne
    @JoinColumn(name = "writer", referencedColumnName = "id")
    private User writer;

    @Builder
    public Writing(String title, String content, String writing_pic, User writer) {
        this.title = title;
        this.content = content;
        this.writing_pic = writing_pic;
        this.writer = writer;
    }

}
