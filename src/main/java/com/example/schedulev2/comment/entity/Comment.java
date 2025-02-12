package com.example.schedulev2.comment.entity;

import com.example.schedulev2.common.entity.BaseEntity;
import com.example.schedulev2.schedule.entity.Schedule;
import com.example.schedulev2.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String contents, User user, Schedule schedule) {
        this.contents = contents;
        this.schedule = schedule;
        this.user = user;
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }
}
