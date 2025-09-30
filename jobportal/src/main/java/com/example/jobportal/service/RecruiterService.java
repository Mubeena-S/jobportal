package com.example.jobportal.service;

import com.example.jobportal.model.Recruiter;
import com.example.jobportal.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterService {

	private final RecruiterRepository recruiterRepository;
	private final PasswordEncoder passwordEncoder;

	// Add new recruiter
	public Recruiter addRecruiter(Recruiter recruiter) {
		// Check if email already exists
		recruiterRepository.findByEmail(recruiter.getEmail()).ifPresent(r -> {
			throw new RuntimeException("Email already registered: " + recruiter.getEmail());
		});

		// Encode password
		recruiter.setPassword(passwordEncoder.encode(recruiter.getPassword()));
		return recruiterRepository.save(recruiter);
	}

	// Get all recruiters
	public List<Recruiter> getAllRecruiters() {
		return recruiterRepository.findAll();
	}

	// Get recruiter by ID
	public Optional<Recruiter> getRecruiterById(Long id) {
		return recruiterRepository.findById(id);
	}

	// Get recruiter by Email
	public Optional<Recruiter> getRecruiterByEmail(String email) {
		return recruiterRepository.findByEmail(email);
	}

	// Update recruiter
	@Transactional
	public Recruiter updateRecruiter(Long id, Recruiter updatedRecruiter) {
		return recruiterRepository.findById(id).map(recruiter -> {
			recruiter.setCompanyName(updatedRecruiter.getCompanyName());
			recruiter.setContactPerson(updatedRecruiter.getContactPerson());
			recruiter.setEmail(updatedRecruiter.getEmail());

			// Only encode and update password if provided
			if (updatedRecruiter.getPassword() != null && !updatedRecruiter.getPassword().isBlank()) {
				recruiter.setPassword(passwordEncoder.encode(updatedRecruiter.getPassword()));
			}

			recruiter.setContactNumber(updatedRecruiter.getContactNumber());
			recruiter.setDesignation(updatedRecruiter.getDesignation());
			recruiter.setCompanyWebsite(updatedRecruiter.getCompanyWebsite());
			recruiter.setCompanyAddress(updatedRecruiter.getCompanyAddress());
			recruiter.setVerified(updatedRecruiter.isVerified());
			return recruiterRepository.save(recruiter);
		}).orElseThrow(() -> new RuntimeException("Recruiter not found with ID: " + id));
	}

	// Delete recruiter
	@Transactional
	public void deleteRecruiter(Long id) {
		if (!recruiterRepository.existsById(id)) {
			throw new RuntimeException("Recruiter not found with ID: " + id);
		}
		recruiterRepository.deleteById(id);
	}
}
