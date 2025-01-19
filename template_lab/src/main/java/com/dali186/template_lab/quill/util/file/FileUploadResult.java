package com.dali186.template_lab.quill.util.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileUploadResult {
    private String originFileName;
    private String savedFilePath;
}
