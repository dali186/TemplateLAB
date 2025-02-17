package com.dali186.template_lab.quill.controller;

import com.dali186.template_lab.quill.dto.BoardDto;
import com.dali186.template_lab.quill.service.QuillService;
import com.dali186.template_lab.quill.util.file.FileUploadResult;
import com.dali186.template_lab.quill.util.file.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/quill")
@RequiredArgsConstructor
public class QuillController {

    private final QuillService quillService;
    private final FileUtil fileUtil;

    /** VIEW **/
    /** 게시글 목록 조회 **/
    @GetMapping("/list")
    public String boardList(Model model) {
        model.addAttribute("boards", quillService.getBoardList());

        return "/quill/list";
    }

    /** 게시글 상세 조회 **/
    @GetMapping("/view/{id}")
    public String boardView(@PathVariable("id") String id, Model model) {
        model.addAttribute("board", quillService.getBoardById(Integer.parseInt(id)));

        return "/quill/view";
    }

    /** 게시글 작성 **/
    @GetMapping("/write")
    public String boardWrite() {

        return "/quill/write";
    }

    /** 게시글 상세 조회 **/
    @GetMapping("/view/smartEditor/{id}")
    public String boardViewBySmartEditor(@PathVariable("id") String id, Model model) {
        model.addAttribute("board", quillService.getBoardById(Integer.parseInt(id)));

        return "/smartEditor2/view";
    }

    /** 게시글 작성 **/
    @GetMapping("/smartEditor/write")
    public String boardWriteBySmartEditor() {

        return "/smartEditor2/write";
    }

    /** API **/
    /** 게시글 등록 및 수정 **/
    @PostMapping("/register")
    public String registerBoard(@ModelAttribute BoardDto boardDto) {
        // 게시글 작성 / 수정 분기
        if (boardDto.getCreatedAt() != null && !"".equals(boardDto.getCreatedAt())) {
            quillService.modifyBoard(boardDto);
        } else {
            quillService.registerBoard(boardDto);
        }

        return "redirect:/quill/list";
    }

    /** 게시글 삭제 **/
    @PostMapping("/remove/{id}")
    public String removeBoard(@PathVariable String id) {
        quillService.removeBoard(Integer.parseInt(id));

        return "redirect:/quill/list";
    }

    /** File 처리 **/
    @ResponseBody
    @GetMapping("/downloadFile")
    public ResponseEntity<?> downloadFile(@RequestParam("filePath") String filePath,
                                          @RequestParam("fileName") String fileName) {

        filePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
        fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        return quillService.downloadFile(filePath, fileName);
    }

    @RequestMapping("/fileDownload/{fileName}")
    public void fileDownload(@PathVariable(value = "fileName") String fileName,
                             HttpServletResponse response) throws IOException {
        File f = new File("/DATA/QUILL", fileName);
        // file 다운로드 설정
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    @ResponseBody
    @PostMapping("/editorFile")
    public ResponseEntity<?> editorFileUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        FileUploadResult result = fileUtil.uploadFile(multipartFile);
        String path = result.getSavedFilePath();

        return ResponseEntity.ok().body(path.substring(path.lastIndexOf("\\") + 1));
    }

    @ResponseBody
    @PostMapping("/editorFile2")
    public String editorFileUpload2(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        String returnUrl = "";
        FileUploadResult result = fileUtil.uploadFile(multipartFile);
        String path = result.getSavedFilePath();
        String fileUUID = path.substring(path.lastIndexOf("\\") + 1);

        returnUrl += "&sFileName=" + path.substring(path.lastIndexOf("\\") + 1);
        returnUrl += "&sFileURL=/quill/editorFile/" + path.substring(path.lastIndexOf("\\") + 1);

        return returnUrl;
    }

    @ResponseBody
    @GetMapping("/editorFile/{fileName}")
    public ResponseEntity<?> editorFileDownload(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) throws IOException {
        // 이미지 파일 경로
        File file = new File("/DATA/QUILL", fileName);

        // 파일이 존재하지 않을 경우 처리
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Spring의 Resource 객체로 파일을 읽음
        Resource resource = new FileSystemResource(file);

        // MIME 타입 설정 (Spring이 자동으로 판단)
        String contentType = Files.probeContentType(file.toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType)) // 파일의 실제 MIME 타입 설정
                .body(resource); // 파일 리소스를 본문으로 반환
    }
}
