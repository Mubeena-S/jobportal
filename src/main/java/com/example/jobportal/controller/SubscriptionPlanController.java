package com.example.jobportal.controller;

import com.example.jobportal.model.SubscriptionPlan;
import com.example.jobportal.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription-plans")
@RequiredArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    // Create new plan
    @PostMapping
    public ResponseEntity<SubscriptionPlan> createPlan(@RequestBody SubscriptionPlan plan) {
        SubscriptionPlan createdPlan = subscriptionPlanService.createPlan(plan);
        return ResponseEntity.ok(createdPlan);
    }

    // Get all plans
    @GetMapping
    public ResponseEntity<List<SubscriptionPlan>> getAllPlans() {
        return ResponseEntity.ok(subscriptionPlanService.getAllPlans());
    }

    // Get plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPlan> getPlanById(@PathVariable Long id) {
        return subscriptionPlanService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get plan by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<SubscriptionPlan> getPlanByName(@PathVariable String name) {
        return subscriptionPlanService.getPlanByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update plan
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPlan> updatePlan(
            @PathVariable Long id,
            @RequestBody SubscriptionPlan updatedPlan
    ) {
        return ResponseEntity.ok(subscriptionPlanService.updatePlan(id, updatedPlan));
    }

    // Delete plan
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
        subscriptionPlanService.deletePlan(id);
        return ResponseEntity.ok("Subscription Plan deleted successfully!");
    }
}

