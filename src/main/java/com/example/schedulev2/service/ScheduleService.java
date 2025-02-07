package com.example.schedulev2.service;

import com.example.exception.BusinessException;
import com.example.schedulev2.config.GlobalExceptionHandler;
import com.example.schedulev2.dto.schedule.ScheduleRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getTitle(),scheduleRequestDto.getContents());
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(
                saveSchedule.getTitle(),
                saveSchedule.getContents(),
                saveSchedule.getCreatedDate(),
                saveSchedule.getUpdatedDate()
        );
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() -> new BusinessException("id 불일치: " + id, HttpStatus.NOT_FOUND));
    }
}
