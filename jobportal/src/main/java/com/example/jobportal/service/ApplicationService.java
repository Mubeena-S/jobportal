package com.example.jobportal.service;

import com.example.jobportal.dto.ApplicationRequestDTO;
import com.example.jobportal.model.Application;
import com.example.jobportal.model.ApplicationStatus;
import com.example.jobportal.model.JobPost;
import com.example.jobportal.model.User;
import com.example.jobportal.repository.ApplicationRepository;
import com.example.jobportal.repository.JobPostRepository;
import com.example.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    // Apply for Job (Candidate - Job Application)
    public Application apply(ApplicationRequestDTO request, String candidateEmail) {
        // 1. get user by JWT
        User user = userRepository.findByEmail(candidateEmail)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        // 2. Find Job
        JobPost jobPost = jobPostRepository.findById(request.getJobPostId())
                .orElseThrow(() -> new RuntimeException("Job not found with id " + request.getJobPostId()));

        // 3. Create application object
        Application application = new Application();
        application.setUser(user);
        application.setJobPost(jobPost);
        application.setCoverLetter(request.getCoverLetter());
        application.setResumeUrl(request.getResumeUrl());
        application.setStatus(ApplicationStatus.PENDING);

        return applicationRepository.save(application);
    }

    // Get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Get applications by JobPost
    public List<Application> getApplicationsByJob(Long jobPostId) {
        return applicationRepository.findByJobPostId(jobPostId);
    }

    //  Get applications by User
    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    // Update status
    public Application updateStatus(Long id, ApplicationStatus status) {
        return applicationRepository.findById(id)
                .map(app -> {
                    app.setStatus(status);
                    return applicationRepository.save(app);
                })
                .orElseThrow(() -> new RuntimeException("Application not found with id " + id));
    }
}
