package com.example.jobportal.service;

import com.example.jobportal.model.AuditLog;
import com.example.jobportal.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    // Save a new audit log (manual log)
    public void logAction(String username, String actionType, String entityName, String entityId, String remarks,
                          String clientIp) {
        AuditLog log = AuditLog.builder()
                .username(username)
                .actionType(actionType)
                .entityName(entityName)
                .entityId(entityId)
                .remarks(remarks)
                .clientIp(clientIp)
                .actionTime(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);
    }

    // Save a new audit log (for POST API)
    public AuditLog saveLog(AuditLog auditLog) {
        if (auditLog.getActionTime() == null) {
            auditLog.setActionTime(LocalDateTime.now());
        }
        return auditLogRepository.save(auditLog);
    }

    // Fetch all logs
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    // Fetch logs by username
    public List<AuditLog> getLogsByUsername(String username) {
        return auditLogRepository.findByUsername(username);
    }

    // Fetch logs by entity name
    public List<AuditLog> getLogsByEntity(String entityName) {
        return auditLogRepository.findByEntityName(entityName);
    }

    // Fetch logs by action type
    public List<AuditLog> getLogsByAction(String actionType) {
        return auditLogRepository.findByActionType(actionType);
    }
}
