package com.example.schedulev2.service;

import com.example.exception.BusinessException;
import com.example.schedulev2.dto.user.UserRequestDto;
import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.dto.user.UserSaveRequestDto;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto.getUsername(),userSaveRequestDto.getEmail(),userSaveRequestDto.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedDate(),
                savedUser.getUpdatedDate()
        );
    }

    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
        user.updateUser(userRequestDto.getEmail(), userRequestDto.getUsername());
        userRepository.saveAndFlush(user);
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
