package com.example.jobportal.dto;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobPostDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private String experienceLevel;
    private Double minSalary;
    private Double maxSalary;
    private List<String> skills;
    private String status;
    private String recruiterEmail;
    private String recruiterCompany;
    private List<JobApplicationDTO> applications;
}
