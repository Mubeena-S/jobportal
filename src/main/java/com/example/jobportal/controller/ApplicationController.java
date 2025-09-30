package com.example.jobportal.controller;

import com.example.jobportal.dto.ApplicationRequestDTO;
import com.example.jobportal.dto.ApplicationResponseDTO;
import com.example.jobportal.model.Application;
import com.example.jobportal.model.ApplicationStatus;
import com.example.jobportal.service.ApplicationService;
import com.example.jobportal.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

	private final ApplicationService applicationService;
	private final JwtUtil jwtUtil;

	// Apply for a job
	@PostMapping
	public ResponseEntity<ApplicationResponseDTO> apply(@RequestBody ApplicationRequestDTO requestDTO,
			@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new RuntimeException("Missing or invalid Authorization header");
		}

		String token = authHeader.substring(7);
		String email = jwtUtil.extractUsername(token);

		if (email == null) {
			throw new RuntimeException("Unauthorized: invalid token");
		}

		Application application = applicationService.apply(requestDTO, email);
		return ResponseEntity.ok(mapToDTO(application));
	}

	// Get all applications
	@GetMapping
	public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
		List<ApplicationResponseDTO> dtos = applicationService.getAllApplications().stream().map(this::mapToDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// Get applications by job post
	@GetMapping("/job/{jobPostId}")
	public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByJob(@PathVariable Long jobPostId) {
		List<ApplicationResponseDTO> dtos = applicationService.getApplicationsByJob(jobPostId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// Get applications by user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByUser(@PathVariable Long userId) {
		List<ApplicationResponseDTO> dtos = applicationService.getApplicationsByUser(userId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// Update application status (case-insensitive)
	@PutMapping("/{applicationId}/status")
	public ResponseEntity<ApplicationResponseDTO> updateStatus(@PathVariable Long applicationId,
			@RequestParam String status) {

		// Convert string to enum safely (case-insensitive)
		ApplicationStatus appStatus;
		try {
			appStatus = ApplicationStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid application status: " + status);
		}

		Application updatedApp = applicationService.updateStatus(applicationId, appStatus);
		return ResponseEntity.ok(mapToDTO(updatedApp));
	}

	// Helper method to convert Application to DTO
	private ApplicationResponseDTO mapToDTO(Application application) {
		ApplicationResponseDTO dto = new ApplicationResponseDTO();
		dto.setId(application.getId());
		dto.setJobPostId(application.getJobPost().getId());
		dto.setUserId(application.getUser().getId());
		dto.setCoverLetter(application.getCoverLetter());
		dto.setResumeUrl(application.getResumeUrl());
		dto.setStatus(application.getStatus());
		dto.setAppliedAt(application.getAppliedAt());
		return dto;
	}
}
