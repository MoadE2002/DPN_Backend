package com.Dpnetworks.dp.controller;

import com.Dpnetworks.dp.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }

        MultipartFile file = files[0];
        try {
            String responseMessage = excelService.saveFileData(file);
            if (responseMessage.equals("Upload complete!")) {
                return ResponseEntity.ok(responseMessage);
            } else {
                return ResponseEntity.badRequest().body(responseMessage);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while processing the file: " + e.getMessage());
        }
    }
}
