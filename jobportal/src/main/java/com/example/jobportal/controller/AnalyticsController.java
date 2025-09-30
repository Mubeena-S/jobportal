package com.example.jobportal.controller;

import com.example.jobportal.dto.DashboardDto;
import com.example.jobportal.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // Dashboard summary endpoint
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDto> getDashboardSummary() {
        DashboardDto dashboard = analyticsService.getDashboardSummary();
        return ResponseEntity.ok(dashboard);
    }
}

