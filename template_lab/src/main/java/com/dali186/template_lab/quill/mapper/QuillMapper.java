package com.dali186.template_lab.quill.mapper;

import com.dali186.template_lab.quill.dto.BoardDto;
import com.dali186.template_lab.quill.dto.BoardFileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuillMapper {
    List<BoardDto> getBoardList();
    BoardDto getBoardById(int boardId);
    Integer registerBoard(BoardDto boardDto);
    Integer registerFile(BoardFileDto fileDto);
    Integer modifyBoard(BoardDto boardDto);
    Integer deleteBoardById(int boardId);
    List<BoardFileDto> getBoardFileList(@Param("boardId") String boardId, @Param("type") String type);
}
