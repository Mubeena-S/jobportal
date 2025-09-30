package com.example.jobportal.repository;

import com.example.jobportal.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    List<JobPost> findByActiveTrue();
    Optional<JobPost> findByIdAndActiveTrue(Long id);
    List<JobPost> findByRecruiterIdAndActiveTrue(Long recruiterId);

    @Query("SELECT j FROM JobPost j WHERE j.recruiter.email = :email AND j.active = true")
    List<JobPost> findByRecruiterEmail(String email);
    List<JobPost> findByStatusAndActiveTrue(String status);
    List<JobPost> findByLocationContainingIgnoreCaseAndActiveTrue(String location);

    List<JobPost> findBySkillsContainingAndActiveTrue(String skill);
}
