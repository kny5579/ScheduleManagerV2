package com.example.schedulev2.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    @NotBlank(message = "필수 입력값입니다.")
    @Size(max = 10, message = "최대 10자까지 입력 가능")
    private String username;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private String email;
}
