package com.example.jobportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paid_subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaidSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Recruiter who purchased the plan
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    @JsonIgnoreProperties({"jobPosts", "password"})
    private Recruiter recruiter;

    // Chosen subscription plan
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonIgnoreProperties({"createdAt", "updatedAt"})
    private SubscriptionPlan subscriptionPlan;

    // Subscription dates
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    // Status (ACTIVE / EXPIRED / CANCELLED)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    // Payment status (PAID / PENDING / FAILED)
    @Builder.Default
    @Column(nullable = false)
    private String paymentStatus = "PAID";

    // Audit fields
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Lifecycle hooks
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = SubscriptionStatus.ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
