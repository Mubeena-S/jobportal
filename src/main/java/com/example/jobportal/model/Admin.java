package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;   

    @Column(nullable = false, unique = true)
    private String email;  

    @Column(nullable = false)
    private String password; 

    @Builder.Default
    @Column(nullable = false)
    private String role = "ADMIN"; 

    @Builder.Default
    private boolean active = true; 

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
