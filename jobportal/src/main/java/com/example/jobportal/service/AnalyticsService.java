package com.example.jobportal.service;

import com.example.jobportal.dto.DashboardDto;
import com.example.jobportal.model.SubscriptionStatus;
import com.example.jobportal.repository.PaidSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final PaidSubscriptionRepository paidSubscriptionRepository;

    // Returns dashboard summary aggregated from paid subscriptions
    public DashboardDto getDashboardSummary() {
        long totalSubscriptions = paidSubscriptionRepository.count();
        long activeSubscriptions = paidSubscriptionRepository.countByStatus(SubscriptionStatus.ACTIVE);
        long expiredSubscriptions = paidSubscriptionRepository.countByStatus(SubscriptionStatus.EXPIRED);
        long cancelledSubscriptions = paidSubscriptionRepository.countByStatus(SubscriptionStatus.CANCELLED);

        // If you want pending as separate, define SubscriptionStatus.PENDING in your enum
        long pendingSubscriptions = 0;

        return DashboardDto.builder()
                .totalSubscriptions(totalSubscriptions)
                .activeSubscriptions(activeSubscriptions)
                .expiredSubscriptions(expiredSubscriptions)
                .cancelledSubscriptions(cancelledSubscriptions) 
                .pendingSubscriptions(pendingSubscriptions)
                .build();
    }
}
