package com.example.jobportal.controller;

import com.example.jobportal.model.Recruiter;
import com.example.jobportal.service.RecruiterService;
import com.example.jobportal.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recruiters")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterController {

    private final RecruiterService recruiterService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // Register Recruiter
    @PostMapping("/register")
    public ResponseEntity<?> registerRecruiter(@Valid @RequestBody Recruiter recruiter) {
        try {
            Recruiter savedRecruiter = recruiterService.addRecruiter(recruiter);
            return ResponseEntity.ok(savedRecruiter);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Login Recruiter
    @PostMapping("/login")
    public ResponseEntity<?> loginRecruiter(@RequestBody Recruiter recruiter) {
        try {
            // Authenticate email & password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            recruiter.getEmail(),
                            recruiter.getPassword()
                    )
            );

            // Fetch recruiter details
            Recruiter existingRecruiter = recruiterService.getRecruiterByEmail(recruiter.getEmail())
                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));

            // Generate JWT token with role "RECRUITER"
            String token = jwtUtil.generateToken(existingRecruiter.getEmail(), "RECRUITER");

            // Return as DTO
            return ResponseEntity.ok(new AuthResponse(token, "RECRUITER"));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("{\"error\":\"Invalid credentials\"}");
        }
    }

    // Get All Recruiters (Admin Only)
    @GetMapping
    public ResponseEntity<?> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    // Get Recruiter by ID (Admin Only)
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecruiterById(@PathVariable Long id) {
        return recruiterService.getRecruiterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Recruiter (Admin Only)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecruiter(@PathVariable Long id, @RequestBody Recruiter recruiterDetails) {
        try {
            Recruiter updated = recruiterService.updateRecruiter(id, recruiterDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Recruiter (Admin Only)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.ok("{\"message\":\"Recruiter deleted successfully\"}");
    }

    //  DTO for Login Response
    @Data
    @AllArgsConstructor
    static class AuthResponse {
        private String token;
        private String role;
    }
}
