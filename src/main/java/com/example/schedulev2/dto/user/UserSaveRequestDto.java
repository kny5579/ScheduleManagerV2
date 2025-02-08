package com.example.schedulev2.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSaveRequestDto {
    private String username;
    private String email;
    private String password;
}
