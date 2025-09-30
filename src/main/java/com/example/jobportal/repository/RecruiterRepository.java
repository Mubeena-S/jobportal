package com.example.jobportal.repository;

import com.example.jobportal.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    Optional<Recruiter> findByEmail(String email);

    Optional<Recruiter> findByCompanyName(String companyName);

    boolean existsByEmailAndIsVerifiedTrue(String email);
}

