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
//
//    private int user_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
