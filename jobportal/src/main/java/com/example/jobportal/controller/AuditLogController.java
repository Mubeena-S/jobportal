package com.example.jobportal.controller;

import com.example.jobportal.model.AuditLog;
import com.example.jobportal.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditLogController {

	private final AuditLogService auditLogService;

	// Get all audit logs
	@GetMapping("/all")
	public List<AuditLog> getAllLogs() {
		return auditLogService.getAllLogs();
	}

	// Get logs by username
	@GetMapping("/user/{username}")
	public List<AuditLog> getLogsByUser(@PathVariable String username) {
		return auditLogService.getLogsByUsername(username);
	}

	// Get logs by entity
	@GetMapping("/entity/{entityName}")
	public List<AuditLog> getLogsByEntity(@PathVariable String entityName) {
		return auditLogService.getLogsByEntity(entityName);
	}

	// Get logs by action type
	@GetMapping("/action/{actionType}")
	public List<AuditLog> getLogsByAction(@PathVariable String actionType) {
		return auditLogService.getLogsByAction(actionType);
	}
	
	// Add new audit log
	@PostMapping("/add")
	public AuditLog addAuditLog(@RequestBody AuditLog auditLog) {
	    return auditLogService.saveLog(auditLog);
	}

}
