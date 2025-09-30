package com.example.jobportal.controller;

import com.example.jobportal.dto.PaidSubscriptionDTO;
import com.example.jobportal.dto.SubscriptionRequestDto;
import com.example.jobportal.service.PaidSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaidSubscriptionController {

    private final PaidSubscriptionService paidSubscriptionService;

    // Only RECRUITER or ADMIN can create subscription
    @PostMapping
    @PreAuthorize("hasAnyAuthority('RECRUITER','ADMIN')")
    public ResponseEntity<PaidSubscriptionDTO> createSubscription(@RequestBody SubscriptionRequestDto dto) {
        return ResponseEntity.ok(paidSubscriptionService.createSubscription(dto));
    }

    // Only RECRUITER or ADMIN can view all subscriptions
    @GetMapping
    @PreAuthorize("hasAnyAuthority('RECRUITER','ADMIN')")
    public ResponseEntity<List<PaidSubscriptionDTO>> getAllSubscriptions() {
        return ResponseEntity.ok(paidSubscriptionService.getAllSubscriptions());
    }

    // Only RECRUITER or ADMIN can view subscription by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RECRUITER','ADMIN')")
    public ResponseEntity<PaidSubscriptionDTO> getSubscriptionById(@PathVariable Long id) {
        return paidSubscriptionService.getSubscriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Only RECRUITER or ADMIN can update subscription
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RECRUITER','ADMIN')")
    public ResponseEntity<PaidSubscriptionDTO> updateSubscription(@PathVariable Long id,
                                                                   @RequestBody SubscriptionRequestDto dto) {
        return ResponseEntity.ok(paidSubscriptionService.updateSubscription(id, dto));
    }

    // Only RECRUITER or ADMIN can delete subscription
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RECRUITER','ADMIN')")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        paidSubscriptionService.deleteSubscription(id);
        return ResponseEntity.ok("{\"message\":\"Subscription deleted successfully\"}");
    }
}
