package com.example.jobportal.controller;

import com.example.jobportal.dto.JobPostDTO;
import com.example.jobportal.model.JobPost;
import com.example.jobportal.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostController {

    private final JobPostService jobPostService;

    // Get all active jobs
    @GetMapping("/all")
    public ResponseEntity<List<JobPostDTO>> getAllJobs() {
        return ResponseEntity.ok(jobPostService.getAllJobs());
    }

    // Get active job by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobPostDTO> getJobById(@PathVariable Long id) {
        return jobPostService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add new job - only RECRUITER role
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public ResponseEntity<JobPostDTO> addJob(
            @RequestBody JobPost jobPost,
            @RequestParam String recruiterEmail
    ) {
        return ResponseEntity.ok(jobPostService.addJobPost(jobPost, recruiterEmail));
    }

    // Update job - only job owner (recruiter) or admin
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('RECRUITER') or hasAuthority('ADMIN')")
    public ResponseEntity<JobPostDTO> updateJob(
            @PathVariable Long id,
            @RequestBody JobPost jobPost
    ) {
        try {
            JobPostDTO updatedJob = jobPostService.updateJobPost(id, jobPost);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Soft Delete job - only job owner (recruiter) or admin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RECRUITER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        try {
            jobPostService.deleteJobPost(id);
            return ResponseEntity.ok("{\"message\":\"Job deleted successfully\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
