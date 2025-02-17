package com.dali186.template_lab.quill.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardDto {
    private int boardId;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private String updatedAt;

    private Boolean isAttachFileChanged;
    private MultipartFile[] attachFiles;
    private List<BoardFileDto> attachFilesMetaData;

    private Boolean isEditorFileChanged;
    private MultipartFile[] editorFiles;
    private List<BoardFileDto> editorFilesMetaData;
}
