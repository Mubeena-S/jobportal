package com.example.jobportal.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileUploadService {

    private final Cloudinary cloudinary;

    public FileUploadService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // Upload file to Cloudinary
    @SuppressWarnings("unchecked")
    public String uploadFile(MultipartFile file) {
        try {
            Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto")
            );
            return uploadResult.get("secure_url").toString(); 
        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    // Delete file from Cloudinary
    @SuppressWarnings("unchecked")
    public String deleteFile(String publicId) {
        try {
            Map<String, Object> result = (Map<String, Object>) cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return result.get("result").toString(); // "ok" if deleted
        } catch (IOException e) {
            throw new RuntimeException("File deletion failed: " + e.getMessage());
        }
    }
}
