package com.example.schedulev2.service;

import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.dto.user.UserSaveRequestDto;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto.getUsername(), userSaveRequestDto.getEmail(), userSaveRequestDto.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedDate(),
                savedUser.getUpdatedDate()
        );
    }

    public boolean login(String email, String password, HttpServletRequest request) {
        // TODO: 이메일, 비번 인증 구현
        // if (!validateUser(email, password)) {
        //    return false;  // 로그인 실패
        // }

        HttpSession session = request.getSession();
        session.setAttribute("sessionKey값", email);

        return true;
    }
}
