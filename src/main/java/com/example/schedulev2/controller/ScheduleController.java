package com.example.schedulev2.controller;

import com.example.schedulev2.dto.schedule.ScheduleRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        return new ResponseEntity<>(scheduleService.findAllSchedules(),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }


}
