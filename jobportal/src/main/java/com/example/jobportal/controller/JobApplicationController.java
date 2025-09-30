package com.example.jobportal.controller;

import com.example.jobportal.dto.JobApplicationDTO;
import com.example.jobportal.repository.JobApplicationRepository;
import com.example.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    private final JobApplicationRepository jobApplicationRepository;

    // Batch update all null coverLetter / resumeUrl fields
    @PutMapping("/fix-null-fields")
    public ResponseEntity<String> fixNullFields() {
        jobApplicationService.fixNullCoverLetterAndResume();
        return ResponseEntity.ok("All null coverLetter and resumeUrl fields updated successfully.");
    }

    // Get all applications with proper DTO mapping
    @GetMapping("/all")
    public ResponseEntity<List<JobApplicationDTO>> getAllApplications() {
        List<JobApplicationDTO> applications = jobApplicationRepository.findAll().stream()
                .map(app -> JobApplicationDTO.builder()
                        .id(app.getId())
                        .jobPostId(app.getJobPost().getId())
                        .userId(app.getUser().getId())
                        .coverLetter(app.getCoverLetter())
                        .resumeUrl(app.getResumeUrl())
                        .status(app.getStatus())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(applications);
    }
}
