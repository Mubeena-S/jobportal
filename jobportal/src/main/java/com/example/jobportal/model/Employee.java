package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String department;
    private double salary;

    private String phoneNumber;
    private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Relation with recruiter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id") 
    private User recruiter; 

    // Auto timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
