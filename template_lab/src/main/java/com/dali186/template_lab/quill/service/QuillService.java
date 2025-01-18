package com.dali186.template_lab.quill.service;

import com.dali186.template_lab.quill.dto.BoardDto;
import com.dali186.template_lab.quill.mapper.QuillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuillService {

    private final QuillMapper mapper;

    @Transactional
    public List<BoardDto> getBoardList() {

        return mapper.getBoardList();
    }

    @Transactional
    public BoardDto getBoardById(int boardId) {

        return mapper.getBoardById(boardId);
    }
}
