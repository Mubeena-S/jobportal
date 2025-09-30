package com.example.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemStatusDTO {

    private String applicationName;   
    private String version;           
    private String status;            
    private LocalDateTime serverTime; 
    private String databaseStatus;    
}
