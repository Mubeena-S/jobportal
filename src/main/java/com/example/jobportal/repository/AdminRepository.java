package com.example.jobportal.repository;

import com.example.jobportal.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Find by email 
    Optional<Admin> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);
}
