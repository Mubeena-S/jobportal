package com.example.jobportal.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDTO {

    private Long jobPostId;      
    private String coverLetter;  
    private String resumeUrl;    
}
