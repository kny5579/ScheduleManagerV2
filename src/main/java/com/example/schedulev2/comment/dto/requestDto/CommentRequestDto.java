package com.example.schedulev2.comment.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private Long userId;

    @NotBlank(message = "필수 입력값입니다.")
    private String contents;
}
