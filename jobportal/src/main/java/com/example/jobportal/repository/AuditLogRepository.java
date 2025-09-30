package com.example.jobportal.repository;

import com.example.jobportal.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUsername(String username);
    List<AuditLog> findByEntityName(String entityName);
    List<AuditLog> findByActionType(String actionType);
}