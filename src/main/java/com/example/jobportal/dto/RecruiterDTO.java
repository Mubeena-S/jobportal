package com.example.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecruiterDTO {

    private Long id;   

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Recruiter email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private String contactNumber;

    private String designation; 

    private String companyWebsite;

    private String companyAddress;

    private boolean isVerified = false; 
}


