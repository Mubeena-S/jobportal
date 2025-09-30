package com.example.jobportal.service;

import com.example.jobportal.dto.PaidSubscriptionDTO;
import com.example.jobportal.dto.SubscriptionRequestDto;
import com.example.jobportal.model.*;
import com.example.jobportal.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaidSubscriptionService {

    private final PaidSubscriptionRepository paidSubscriptionRepository;
    private final RecruiterRepository recruiterRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    // Create a new subscription
    @Transactional
    public PaidSubscriptionDTO createSubscription(SubscriptionRequestDto request) {
        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found with ID: " + request.getRecruiterId()));

        SubscriptionPlan plan = subscriptionPlanRepository.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Subscription plan not found with ID: " + request.getPlanId()));

        PaidSubscription subscription = PaidSubscription.builder()
                .recruiter(recruiter)
                .subscriptionPlan(plan)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusMonths(plan.getDurationMonths() != null ? plan.getDurationMonths() : 1))
                .status(SubscriptionStatus.ACTIVE)
                .build();

        return mapToDTO(paidSubscriptionRepository.save(subscription));
    }

    // Update subscription
    @Transactional
    public PaidSubscriptionDTO updateSubscription(Long subscriptionId, SubscriptionRequestDto request) {
        PaidSubscription subscription = paidSubscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID: " + subscriptionId));

        SubscriptionPlan plan = subscriptionPlanRepository.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Subscription plan not found with ID: " + request.getPlanId()));

        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found with ID: " + request.getRecruiterId()));

        subscription.setRecruiter(recruiter);
        subscription.setSubscriptionPlan(plan);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(plan.getDurationMonths() != null ? plan.getDurationMonths() : 1));
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        return mapToDTO(subscription);
    }

    // Get subscription by ID
    @Transactional
    public Optional<PaidSubscriptionDTO> getSubscriptionById(Long id) {
        return paidSubscriptionRepository.findById(id).map(this::mapToDTO);
    }

    // Get all subscriptions
    @Transactional
    public List<PaidSubscriptionDTO> getAllSubscriptions() {
        return paidSubscriptionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get active subscriptions
    @Transactional
    public List<PaidSubscriptionDTO> getActiveSubscriptions() {
        return paidSubscriptionRepository.findByStatus(SubscriptionStatus.ACTIVE)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get subscriptions by recruiter
    @Transactional
    public List<PaidSubscriptionDTO> getSubscriptionsByRecruiter(Long recruiterId) {
        return paidSubscriptionRepository.findByRecruiterId(recruiterId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Expire subscriptions if endDate has passed
    @Transactional
    public void checkAndExpireSubscriptions() {
        List<PaidSubscription> subscriptions = paidSubscriptionRepository.findAll();
        for (PaidSubscription subscription : subscriptions) {
            if (subscription.getEndDate().isBefore(LocalDateTime.now()) &&
                subscription.getStatus() == SubscriptionStatus.ACTIVE) {
                subscription.setStatus(SubscriptionStatus.EXPIRED);
                paidSubscriptionRepository.save(subscription);
            }
        }
    }

    // Delete subscription
    @Transactional
    public void deleteSubscription(Long id) {
        if (!paidSubscriptionRepository.existsById(id)) {
            throw new RuntimeException("Subscription with ID " + id + " not found");
        }
        paidSubscriptionRepository.deleteById(id);
    }

    // Map entity - DTO
    private PaidSubscriptionDTO mapToDTO(PaidSubscription subscription) {
        return PaidSubscriptionDTO.builder()
                .id(subscription.getId())
                .recruiterId(subscription.getRecruiter().getId())
                .recruiterEmail(subscription.getRecruiter().getEmail())
                .recruiterCompany(subscription.getRecruiter().getCompanyName())
                .planId(subscription.getSubscriptionPlan().getId())
                .planName(subscription.getSubscriptionPlan().getName())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .status(subscription.getStatus())
                .paymentStatus(subscription.getPaymentStatus())
                .build();
    }
}
