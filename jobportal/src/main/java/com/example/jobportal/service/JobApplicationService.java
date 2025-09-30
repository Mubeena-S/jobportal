package com.example.jobportal.service;

import com.example.jobportal.model.JobApplication;
import com.example.jobportal.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ✅ Spring transactional

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    @Transactional
    public void fixNullCoverLetterAndResume() {
        List<JobApplication> applications = jobApplicationRepository.findAll();

        for (JobApplication app : applications) {
            boolean updated = false;

            if (app.getCoverLetter() == null) {
                app.setCoverLetter("I am very interested in this job.");
                updated = true;
            }

            if (app.getResumeUrl() == null) {
                app.setResumeUrl("https://example.com/resume.pdf");
                updated = true;
            }

            if (updated) {
                jobApplicationRepository.save(app);
            }
        }
    }
}
