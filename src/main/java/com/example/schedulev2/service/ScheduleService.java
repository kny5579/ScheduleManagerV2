package com.example.schedulev2.service;

import com.example.schedulev2.dto.schedule.ScheduleRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
