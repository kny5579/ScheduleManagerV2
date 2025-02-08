package com.example.schedulev2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Schedule(String title, String contents,User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void updateSchedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
