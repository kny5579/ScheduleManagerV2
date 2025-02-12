package com.example.schedulev2.comment.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
}
