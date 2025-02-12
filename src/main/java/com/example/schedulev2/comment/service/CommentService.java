package com.example.schedulev2.comment.service;

import com.example.schedulev2.comment.dto.requestDto.CommentRequestDto;
import com.example.schedulev2.comment.dto.responseDto.CommentResponseDto;
import com.example.schedulev2.comment.entity.Comment;
import com.example.schedulev2.comment.repository.CommentRepository;
import com.example.schedulev2.common.exception.BusinessException;
import com.example.schedulev2.schedule.entity.Schedule;
import com.example.schedulev2.schedule.repository.ScheduleRepository;
import com.example.schedulev2.user.entity.User;
import com.example.schedulev2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentResponseDto saveComment(Long scheduleId, CommentRequestDto commentRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException("해당 일정이 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        User user = userRepository.findById(commentRequestDto.getUserId())
                .orElseThrow(() -> new BusinessException("해당 유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        Comment comment = new Comment(commentRequestDto.getContents(),user,schedule);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(
                savedComment.getContents(),
                savedComment.getCreatedDate(),
                savedComment.getUpdatedDate()
        );
    }



}
