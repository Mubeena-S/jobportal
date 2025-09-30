package com.example.jobportal.controller;

import com.example.jobportal.dto.AuthenticationResponse;
import com.example.jobportal.dto.LoginRequest;
import com.example.jobportal.dto.RegisterRequest;
import com.example.jobportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    // Login existing user
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // Test API (for checking server status)
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("AuthController is working fine!");
    }
}

