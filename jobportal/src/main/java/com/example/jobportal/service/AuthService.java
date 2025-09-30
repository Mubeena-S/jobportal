package com.example.jobportal.service;

import com.example.jobportal.dto.AuthenticationResponse;
import com.example.jobportal.dto.LoginRequest;
import com.example.jobportal.dto.RegisterRequest;
import com.example.jobportal.model.Role;
import com.example.jobportal.model.User;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register user
    public AuthenticationResponse register(RegisterRequest request) {

        // Assign default role
        Role role = Role.USER; 
        // Check duplicate email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        // Build User entity
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);

        // Generate JWT token
        String accessToken = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .message("User registered successfully as " + role.name() + "!")
                .build();
    }

    // Login user
    public AuthenticationResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token
        String accessToken = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .message("Login successful!")
                .build();
    }
}
