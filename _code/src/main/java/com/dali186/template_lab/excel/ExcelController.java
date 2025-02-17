package com.dali186.template_lab.excel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExcelController {

    @GetMapping("/downloadExcel")
    public ResponseEntity<?> downloadExcel() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
