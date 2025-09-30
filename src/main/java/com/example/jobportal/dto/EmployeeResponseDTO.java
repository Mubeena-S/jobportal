package com.example.jobportal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private double salary;
    private String phoneNumber;
    private String address;
    private Long recruiterId; 
}
