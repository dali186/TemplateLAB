package com.dali186.template_lab.quill.service;

import com.dali186.template_lab.quill.dto.BoardDto;
import com.dali186.template_lab.quill.dto.BoardFileDto;
import com.dali186.template_lab.quill.mapper.QuillMapper;
import com.dali186.template_lab.quill.util.file.FileUploadResult;
import com.dali186.template_lab.quill.util.file.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuillService {

    private final QuillMapper mapper;
    private final FileUtil fileUtil;

    @Transactional
    private void uploadBoardFiles(BoardDto boardDto, String type) {

        for (MultipartFile file : boardDto.getAttachFiles()) {
            try {
                FileUploadResult result = fileUtil.uploadFile(file);

                BoardFileDto fileDto = new BoardFileDto();
                fileDto.setFileInfo(result);
                fileDto.setType(type);
                fileDto.setBoardId(boardDto.getBoardId());

                mapper.registerFile(fileDto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Transactional
    public List<BoardDto> getBoardList() {

        return mapper.getBoardList();
    }

    @Transactional
    public BoardDto getBoardById(int boardId) {
        BoardDto boardDto = mapper.getBoardById(boardId);
        boardDto.setAttachFilesMetaData(mapper.getBoardFileList(String.valueOf(boardId), "ATTACH"));
        boardDto.setEditorFilesMetaData(mapper.getBoardFileList(String.valueOf(boardId), "EDITOR"));

        return boardDto;
    }

    @Transactional
    public void registerBoard(BoardDto boardDto) {
        mapper.registerBoard(boardDto);

        if (boardDto.getIsAttachFileChanged() != null && boardDto.getIsAttachFileChanged()) {
            this.uploadBoardFiles(boardDto, "ATTACH");
        }
        if (boardDto.getIsEditorFileChanged() != null && boardDto.getIsEditorFileChanged()) {
            this.uploadBoardFiles(boardDto, "EDITOR");
        }
    }

    @Transactional
    public void modifyBoard(BoardDto boardDto) {
        int result;

        result = mapper.modifyBoard(boardDto);
    }

    @Transactional
    public void removeBoard(int boardId) {

        int result = mapper.deleteBoardById(boardId);
    }

    public ResponseEntity<?> downloadFile(String path, String name) {
        return fileUtil.downloadFile(path, name);
    }
}
