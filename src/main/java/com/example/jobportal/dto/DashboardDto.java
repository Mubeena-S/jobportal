package com.example.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDto {

    private long totalUsers;
    private long totalRecruiters;
    private long totalJobs;
    private long totalApplications;

    private long activeSubscriptions;
    private long inactiveSubscriptions;
    private long pendingSubscriptions;   
    private long totalSubscriptions;     
    private long expiredSubscriptions;
    private long cancelledSubscriptions;
    
    private long successfulPayments;
    private long failedPayments;
}

