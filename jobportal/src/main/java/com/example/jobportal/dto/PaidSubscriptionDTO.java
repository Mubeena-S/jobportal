package com.example.jobportal.dto;

import com.example.jobportal.model.SubscriptionStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaidSubscriptionDTO {
    private Long id;
    private Long recruiterId;
    private String recruiterEmail;
    private String recruiterCompany;
    private Long planId;
    private String planName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SubscriptionStatus status;
    private String paymentStatus;
}
