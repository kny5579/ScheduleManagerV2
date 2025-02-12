package com.example.schedulev2.schedule.repository;

import com.example.schedulev2.schedule.dto.pagingDto.SchedulePagingDto;
import com.example.schedulev2.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("""
                SELECT new com.example.schedulev2.schedule.dto.pagingDto.SchedulePagingDto(
                    s.title, s.contents, s.createdDate, s.updatedDate, u.username, COUNT(c.id)
                )
                FROM Schedule s
                JOIN s.user u
                LEFT JOIN Comment c ON c.schedule = s
                GROUP BY s.id, u.username
                ORDER BY s.updatedDate DESC
            """)
    Page<SchedulePagingDto> findSchedulesByPage(Pageable pageable);
}
