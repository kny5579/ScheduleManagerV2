package com.example.schedulev2.user.service;

import com.example.schedulev2.common.config.PasswordEncoder;
import com.example.schedulev2.common.exception.BusinessException;
import com.example.schedulev2.user.dto.requestDto.UserLoginRequestDto;
import com.example.schedulev2.user.dto.requestDto.UserSaveRequestDto;
import com.example.schedulev2.user.dto.responseDto.UserResponseDto;
import com.example.schedulev2.user.entity.User;
import com.example.schedulev2.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        String password = passwordEncoder.encode(userSaveRequestDto.getPassword());
        if (userRepository.findByEmail(userSaveRequestDto.getEmail()).isPresent()) {
            throw new BusinessException("동일한 이메일이 이미 존재합니다.", HttpStatus.CONFLICT);
        }
        User user = new User(userSaveRequestDto.getUsername(), userSaveRequestDto.getEmail(), password);
        User savedUser = userRepository.save(user);
        return new UserResponseDto(
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedDate(),
                savedUser.getUpdatedDate()
        );
    }

    public void login(UserLoginRequestDto userLoginRequestDto, HttpServletRequest request) {
        String password = userLoginRequestDto.getPassword();
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(() -> new BusinessException("이메일이 일치하지 않습니다.", HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        HttpSession session = request.getSession();
        session.setAttribute("sessionKey값", userLoginRequestDto.getEmail());
    }
}
