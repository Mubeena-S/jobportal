package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Plan name (Basic, Standard, Premium, etc.)
    @Column(nullable = false, unique = true)
    private String name;

    // Plan description
    @Column(length = 500)
    private String description;

    // Plan price (monthly / yearly basis)
    @Column(nullable = false)
    private Double price;

    // Plan duration (in days )
    @Column(nullable = false)
    private Integer durationInDays;

    // how much recruiter can make maximum job posts 
    @Column(nullable = false)
    private Integer jobPostLimit;

    // when created plan
    private LocalDateTime createdAt;

    // when plan updated
    private LocalDateTime updatedAt;

    // Automatically set timestamps
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Duration in months 
    public Integer getDurationMonths() {
        if (durationInDays == null) return 0;
        return durationInDays / 30; // Approximate 1 month = 30 days
    }
}
