package com.example.schedulev2.schedule.service;

import com.example.schedulev2.common.exception.BusinessException;
import com.example.schedulev2.schedule.dto.pagingDto.SchedulePagingDto;
import com.example.schedulev2.schedule.dto.requestDto.ScheduleUpdateRequestDto;
import com.example.schedulev2.schedule.dto.responseDto.ScheduleResponseDto;
import com.example.schedulev2.schedule.dto.requestDto.ScheduleSaveRequestDto;
import com.example.schedulev2.schedule.entity.Schedule;
import com.example.schedulev2.user.entity.User;
import com.example.schedulev2.schedule.repository.ScheduleRepository;
import com.example.schedulev2.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto saveSchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        User user = userRepository.findById(scheduleSaveRequestDto.getUserId())
                .orElseThrow(() -> new BusinessException("해당 회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        Schedule schedule = new Schedule(scheduleSaveRequestDto.getTitle(), scheduleSaveRequestDto.getContents(),user);
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

    public Page<SchedulePagingDto> findScheduleByPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize); // PageRequest는 0부터 시작
        return scheduleRepository.findSchedulesByPage(pageable);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BusinessException("id가 존재하지 않습니다.: " + id, HttpStatus.NOT_FOUND));
        schedule.updateSchedule(scheduleUpdateRequestDto.getTitle(), scheduleUpdateRequestDto.getContents());
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
