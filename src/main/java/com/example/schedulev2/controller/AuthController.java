package com.example.schedulev2.controller;

import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.dto.user.UserSaveRequestDto;
import com.example.schedulev2.service.AuthService;
import com.example.schedulev2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return new ResponseEntity<>(authService.saveUser(userSaveRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserSaveRequestDto userSaveRequestDto,
            HttpServletRequest request
    ) {
        boolean loginService = authService.login(userSaveRequestDto.getEmail(), userSaveRequestDto.getPassword(), request);
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("로그아웃 완료");
        return "로그아웃 성공!";
    }
}
