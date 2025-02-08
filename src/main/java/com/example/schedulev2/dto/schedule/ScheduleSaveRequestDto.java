package com.example.schedulev2.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleSaveRequestDto {
    private Long userId;
    private String title;
    private String contents;
}
