package com.example.jobportal.dto;

import com.example.jobportal.model.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusUpdateDTO {

    private Long applicationId;         
    private ApplicationStatus status;   
}


