package com.example.schedulev2.comment.controller;

import com.example.schedulev2.comment.dto.requestDto.CommentRequestDto;
import com.example.schedulev2.comment.dto.responseDto.CommentResponseDto;
import com.example.schedulev2.comment.service.CommentService;
import com.example.schedulev2.schedule.dto.requestDto.ScheduleSaveRequestDto;
import com.example.schedulev2.schedule.dto.requestDto.ScheduleUpdateRequestDto;
import com.example.schedulev2.schedule.dto.responseDto.ScheduleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId,
                                                            @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.saveComment(scheduleId, commentRequestDto), HttpStatus.CREATED);
    }

    //schedule의 comment read
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> findCommentByScheduleId(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(commentService.findCommentByScheduleId(scheduleId), HttpStatus.OK);
    }

    //comment id를 통한 개별 comment read
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findCommentById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.updateComment(id, commentRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }



}
