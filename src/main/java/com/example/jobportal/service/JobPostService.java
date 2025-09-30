package com.example.jobportal.service;

import com.example.jobportal.dto.JobApplicationDTO;
import com.example.jobportal.dto.JobPostDTO;
import com.example.jobportal.model.JobPost;
import com.example.jobportal.model.Recruiter;
import com.example.jobportal.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final RecruiterService recruiterService;

    // Add new Job Post
    public JobPostDTO addJobPost(JobPost jobPost, String recruiterEmail) {
        Recruiter recruiter = recruiterService.getRecruiterByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found with email: " + recruiterEmail));

        jobPost.setRecruiter(recruiter);
        jobPost.setActive(true); 
        JobPost savedJob = jobPostRepository.save(jobPost);
        return mapToDTO(savedJob);
    }

    // Get all active jobs
    public List<JobPostDTO> getAllJobs() {
        return jobPostRepository.findAll()
                .stream()
                .filter(JobPost::isActive) 
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get job by ID (only if active)
    public Optional<JobPostDTO> getJobById(Long id) {
        return jobPostRepository.findById(id)
                .filter(JobPost::isActive) 
                .map(this::mapToDTO);
    }

    // Update Job Post
    public JobPostDTO updateJobPost(Long jobId, JobPost updatedJob) {
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));

        if (!jobPost.isActive()) {
            throw new RuntimeException("Cannot update deleted job");
        }

        jobPost.setTitle(updatedJob.getTitle());
        jobPost.setDescription(updatedJob.getDescription());
        jobPost.setLocation(updatedJob.getLocation());
        jobPost.setJobType(updatedJob.getJobType());
        jobPost.setExperienceLevel(updatedJob.getExperienceLevel());
        jobPost.setMinSalary(updatedJob.getMinSalary());
        jobPost.setMaxSalary(updatedJob.getMaxSalary());
        jobPost.setSkills(updatedJob.getSkills() != null ? updatedJob.getSkills() : List.of());
        jobPost.setStatus(updatedJob.getStatus());

        JobPost savedJob = jobPostRepository.save(jobPost);
        return mapToDTO(savedJob);
    }

    // Soft Delete Job Post
    public void deleteJobPost(Long jobId) {
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));

        jobPost.setActive(false); 
        jobPostRepository.save(jobPost);
    }

    // Map JobPost entity - JobPostDTO
    private JobPostDTO mapToDTO(JobPost job) {
        List<JobApplicationDTO> applicationDTOs = job.getApplications() != null
                ? job.getApplications().stream()
                    .map(app -> JobApplicationDTO.builder()
                            .id(app.getId())
                            .jobPostId(app.getJobPost().getId())
                            .userId(app.getUser().getId())
                            .coverLetter(app.getCoverLetter() != null ? app.getCoverLetter() : "I am very interested in this job.")
                            .resumeUrl(app.getResumeUrl() != null ? app.getResumeUrl() : "https://example.com/resume.pdf")
                            .status(app.getStatus())
                            .build())
                    .collect(Collectors.toList())
                : List.of();

        return JobPostDTO.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .experienceLevel(job.getExperienceLevel())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .skills(job.getSkills() != null ? job.getSkills() : List.of())
                .status(job.getStatus())
                .recruiterEmail(job.getRecruiter().getEmail())
                .recruiterCompany(job.getRecruiter().getCompanyName())
                .applications(applicationDTOs)
                .build();
    }
}
