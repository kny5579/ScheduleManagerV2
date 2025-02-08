package com.example.schedulev2.dto.user;

import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private final String username;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
        this.updatedDate = user.getUpdatedDate();
    }
}
