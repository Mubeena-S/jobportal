package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    // Action type (CREATE, UPDATE, DELETE, LOGIN, LOGOUT)
    @Column(nullable = false)
    private String actionType;

    // Entity affected (User, Invoice, Recruiter etc.)
    @Column(nullable = false)
    private String entityName;

    private String entityId;

    @Column(nullable = false)
    private LocalDateTime actionTime;

    // Optional remarks / details
    private String remarks;

    private String clientIp;

    @PrePersist
    protected void onCreate() {
        this.actionTime = LocalDateTime.now();
    }
}

