package com.greenity.server.community.domain;

import com.greenity.server.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name = "heart_count", nullable = false)
    private Long heartCount;

    @ManyToOne
    @JoinColumn(name = "writer", referencedColumnName = "id")
    private User writer;

    @OneToMany(mappedBy = "originalWriting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "originalWriting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;

    @Builder
    public Writing(String title, String content, String writing_pic, Long heartCount, User writer) {
        this.title = title;
        this.content = content;
        this.writing_pic = writing_pic;
        this.heartCount = heartCount;
        this.writer = writer;
    }

}
