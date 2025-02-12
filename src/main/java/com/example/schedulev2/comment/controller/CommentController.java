package com.example.schedulev2.comment.controller;

import com.example.schedulev2.comment.dto.requestDto.CommentRequestDto;
import com.example.schedulev2.comment.dto.responseDto.CommentResponseDto;
import com.example.schedulev2.comment.service.CommentService;
import com.example.schedulev2.schedule.dto.requestDto.ScheduleSaveRequestDto;
import com.example.schedulev2.schedule.dto.responseDto.ScheduleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id,
                                                            @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.saveComment(id, commentRequestDto), HttpStatus.CREATED);
    }


}
