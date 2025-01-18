package com.dali186.template_lab.quill.controller;

import com.dali186.template_lab.quill.service.QuillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quill")
public class QuillController {

    @Autowired
    private QuillService quillService;

    /** 게시글 목록 조회 **/
    @GetMapping("/list")
    public String boardList(Model model) {

        model.addAttribute("boards", quillService.getBoardList());
        return "/quill/list";
    }

    @GetMapping("/view/{id}")
    public String boardView(@PathVariable("id") String id, Model model) {

        model.addAttribute("board", quillService.getBoardById(Integer.parseInt(id)));
        return "/quill/view";
    }

    @GetMapping("/write")
    public String boardWrite() {

        return "/quill/write";
    }
}
