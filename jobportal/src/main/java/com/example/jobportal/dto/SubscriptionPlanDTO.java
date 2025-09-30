package com.example.jobportal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanDTO {

    private Long id;              
    private String name;          
    private String description;   
    private Integer durationInDays; 
    private Double price;         
    private Integer jobPostLimit; 
    private String supportLevel;  
    private Boolean isActive;     
}