package com.example.schedulev2.service;

import com.example.exception.BusinessException;
import com.example.schedulev2.config.PasswordEncoder;
import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.dto.user.UserSaveRequestDto;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.UserRepository;
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
        if(userRepository.findByEmail(userSaveRequestDto.getEmail()).isPresent()) {
            throw new BusinessException("동일한 이메일이 이미 존재합니다.",HttpStatus.UNAUTHORIZED);
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

    public void login(UserSaveRequestDto userSaveRequestDto, HttpServletRequest request) {
        String password = userSaveRequestDto.getPassword();
        User user = userRepository.findByEmail(userSaveRequestDto.getEmail())
                .orElseThrow(() -> new BusinessException("이메일이 일치하지 않습니다.", HttpStatus.UNAUTHORIZED));

        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new BusinessException("비밀번호가 일치하지 않습니다.",HttpStatus.UNAUTHORIZED);
        }

        HttpSession session = request.getSession();
        session.setAttribute("sessionKey값", userSaveRequestDto.getEmail());
    }
}
