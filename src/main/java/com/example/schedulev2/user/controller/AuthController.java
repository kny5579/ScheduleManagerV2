package com.example.schedulev2.user.controller;

import com.example.schedulev2.user.dto.requestDto.UserLoginRequestDto;
import com.example.schedulev2.user.dto.requestDto.UserSaveRequestDto;
import com.example.schedulev2.user.dto.responseDto.UserResponseDto;
import com.example.schedulev2.user.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        return new ResponseEntity<>(authService.saveUser(userSaveRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody UserLoginRequestDto userLoginRequestDto,
            HttpServletRequest request
    ) {
        authService.login(userLoginRequestDto, request);
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
