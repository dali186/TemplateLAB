package com.dali186.template_lab.quill.mapper;

import com.dali186.template_lab.quill.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuillMapper {
    List<BoardDto> getBoardList();
    BoardDto getBoardById(int boardId);
    Integer registerBoard(BoardDto boardDto);
    Integer modifyBoard(int boardId);
}
