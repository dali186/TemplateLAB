package com.dali186.template_lab.quill.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private int boardId;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private String updatedAt;
}
