package com.example.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // Getter + Setter + toString + equals/hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionRequestDto {

    private Long recruiterId;   
    private Long planId;        
}

