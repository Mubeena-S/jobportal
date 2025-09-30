package com.example.jobportal.repository;

import com.example.jobportal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    // Custom query to find employees by recruiter
    List<Employee> findByRecruiterId(Long recruiterId);
}
