package com.example.jobportal.dto;

import com.example.jobportal.model.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO {

    private Long id;                
    private Long jobPostId;         
    private Long userId;            
    private String coverLetter;     
    private String resumeUrl;       
    private ApplicationStatus status; 
    private LocalDateTime appliedAt;  
}

