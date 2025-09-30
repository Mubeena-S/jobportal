package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // New fields
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 60) 
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; 

    // Security & Login Tracking
    private LocalDateTime lastLogin;
    private int failedLoginAttempts;

    @Builder.Default
    private boolean accountLocked = false;

    private LocalDateTime passwordChangedAt;

    // Profile Information
    private String profilePictureUrl;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;

    // Account Status
    @Builder.Default
    private boolean isVerified = false;

    private String lastPasswordResetToken;

    // Auto Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


