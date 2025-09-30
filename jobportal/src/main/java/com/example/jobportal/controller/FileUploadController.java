package com.example.jobportal.controller;

import com.example.jobportal.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    // Upload file to Cloudinary
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = fileUploadService.uploadFile(file);
        return ResponseEntity.ok(url);
    }

    // Delete file from Cloudinary
    @DeleteMapping("/delete/{publicId}")
    public ResponseEntity<String> deleteFile(@PathVariable String publicId) {
        String result = fileUploadService.deleteFile(publicId);
        return ResponseEntity.ok("Delete status: " + result);
    }
}
