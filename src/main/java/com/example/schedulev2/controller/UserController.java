package com.example.schedulev2.controller;

import com.example.schedulev2.dto.user.UserRequestDto;
import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.dto.user.UserSaveRequestDto;
import com.example.schedulev2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return new ResponseEntity<>(userService.saveUser(userSaveRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.updateUser(id, userRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

}
