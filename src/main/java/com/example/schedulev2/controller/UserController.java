package com.example.schedulev2.controller;

import com.example.schedulev2.dto.schedule.ScheduleRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.dto.user.UserRequestDto;
import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.saveUser(userRequestDto), HttpStatus.CREATED);
    }
}
