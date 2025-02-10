package com.example.schedulev2.controller;

import com.example.schedulev2.dto.user.UserUpdateRequestDto;
import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/find")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return new ResponseEntity<>(userService.updateUser(id, userUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

}
