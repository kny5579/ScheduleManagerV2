package com.example.schedulev2.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final String title;
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

}
