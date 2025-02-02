package com.dali186.template_lab.quill.dto;

import com.dali186.template_lab.quill.util.file.FileUploadResult;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class BoardFileDto {
    private String fileId;
    private String type;
    private int boardId;
    private String fileOriginName;
    private String fileName;
    private String filePath;
    private String uploadedAt;


    public void setFileInfo(FileUploadResult result) {
        Path path = Paths.get(result.getSavedFilePath());
        this.setFileOriginName(result.getOriginFileName());
        this.setFileName(path.getFileName().toString());

        Path parentPath = path.getParent();
        this.setFilePath(parentPath != null ? parentPath.toString() : "");
    }
}
