package com.example.jobportal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplicationDTO {

    private Long id;
    private Long jobPostId;
    private Long userId;
    private String coverLetter;
    private String resumeUrl;
    private String status;

    public String getCoverLetter() {
        return coverLetter != null ? coverLetter : "I am very interested in this job.";
    }

    public String getResumeUrl() {
        return resumeUrl != null ? resumeUrl : "https://example.com/resume.pdf";
    }
}
