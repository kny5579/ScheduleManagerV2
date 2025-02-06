package com.example.schedulev2.repository;

import com.example.schedulev2.dto.schedule.ScheduleRequestDto;
import com.example.schedulev2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
