package com.example.jobportal.service;

import com.example.jobportal.model.SubscriptionPlan;
import com.example.jobportal.repository.SubscriptionPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    // Create new subscription plan
    public SubscriptionPlan createPlan(SubscriptionPlan plan) {
        if (subscriptionPlanRepository.existsByName(plan.getName())) {
            throw new RuntimeException("Plan with name '" + plan.getName() + "' already exists!");
        }
        return subscriptionPlanRepository.save(plan);
    }

    // Get all subscription plans
    public List<SubscriptionPlan> getAllPlans() {
        return subscriptionPlanRepository.findAll();
    }

    // Get plan by ID
    public Optional<SubscriptionPlan> getPlanById(Long id) {
        return subscriptionPlanRepository.findById(id);
    }

    // Get plan by name
    public Optional<SubscriptionPlan> getPlanByName(String name) {
        return subscriptionPlanRepository.findByName(name);
    }

    // Update existing plan
    public SubscriptionPlan updatePlan(Long id, SubscriptionPlan updatedPlan) {
        return subscriptionPlanRepository.findById(id)
                .map(plan -> {
                    plan.setName(updatedPlan.getName());
                    plan.setDescription(updatedPlan.getDescription());
                    plan.setPrice(updatedPlan.getPrice());
                    plan.setDurationInDays(updatedPlan.getDurationInDays());
                    return subscriptionPlanRepository.save(plan);
                })
                .orElseThrow(() -> new RuntimeException("Subscription Plan not found with id: " + id));
    }

    // Delete plan by ID
    public void deletePlan(Long id) {
        if (!subscriptionPlanRepository.existsById(id)) {
            throw new RuntimeException("Plan not found with id: " + id);
        }
        subscriptionPlanRepository.deleteById(id);
    }
}
