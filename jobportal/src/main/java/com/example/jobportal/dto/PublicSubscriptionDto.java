package com.example.jobportal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicSubscriptionDto {
    private Long id;                
    private String name;            
    private String description;     
    private double price;           
    private int durationInDays;     
    private int maxJobPosts;        
    private boolean featured;       
}
