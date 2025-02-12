package com.example.schedulev2.comment.dto.responseDto;

import com.example.schedulev2.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.createdDate = comment.getCreatedDate();
        this.updatedDate = comment.getUpdatedDate();
    }
}
