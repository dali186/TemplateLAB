package com.dali186.template_lab.quill.util.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FileUtil {

    @Value("${file.upload.dir}")
    private String uploadPath;

    public FileUploadResult uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String originFileName = file.getOriginalFilename();
        String fileExtension = originFileName.substring(originFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        File uploadFile = new File(uploadPath, uniqueFileName);

        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }

        file.transferTo(uploadFile);

        return new FileUploadResult(originFileName, uploadFile.getAbsolutePath());
    }

    public ResponseEntity<?> downloadFile(String filePath, String fileName) {
        try {
            // 경로와 파일명을 File.separator로 이어붙입니다.
            File file = new File(uploadPath + File.separator + filePath +
                    File.separator + fileName);

            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<script>alert('파일이 존재하지 않습니다.');history.back();</script>");
            }

            // 파일 리소스를 준비합니다.
            Resource resource = new FileSystemResource(file);

            HttpHeaders headers = new HttpHeaders();
            // 파일 이름을 헤더에 올바르게 설정합니다.
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            // 파일을 반환하는 ResponseEntity 생성
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
