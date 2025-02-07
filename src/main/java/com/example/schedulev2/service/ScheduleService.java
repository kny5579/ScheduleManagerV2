package com.example.schedulev2.service;

import com.example.exception.BusinessException;
import com.example.schedulev2.dto.schedule.ScheduleRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getTitle(), scheduleRequestDto.getContents());
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(
                saveSchedule.getTitle(),
                saveSchedule.getContents(),
                saveSchedule.getCreatedDate(),
                saveSchedule.getUpdatedDate()
        );
    }

    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
        schedule.updateSchedule(scheduleRequestDto.getTitle(), scheduleRequestDto.getContents());
        scheduleRepository.saveAndFlush(schedule);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
        scheduleRepository.delete(schedule);
    }

}
