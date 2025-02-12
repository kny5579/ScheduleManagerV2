package com.example.schedulev2.schedule.dto.pagingDto;

import com.example.schedulev2.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulePagingDto extends BaseEntity {
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String username;
    private Long commentCount;




}
