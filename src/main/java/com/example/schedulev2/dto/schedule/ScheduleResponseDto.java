package com.example.schedulev2.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
