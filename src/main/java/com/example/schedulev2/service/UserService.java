package com.example.schedulev2.service;

import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.dto.user.UserRequestDto;
import com.example.schedulev2.dto.user.UserResponseDto;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getUsername(),userRequestDto.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedDate(),
                savedUser.getUpdatedDate()
        );
    }
}
