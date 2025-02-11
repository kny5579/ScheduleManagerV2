package com.example.schedulev2.schedule.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleSaveRequestDto {
    private Long userId;

    @NotBlank(message = "필수 입력값입니다.")
    @Size(max = 10, message = "최대 10자까지 입력 가능")
    private String title;

    @NotBlank(message = "필수 입력값입니다.")
    private String contents;
}
