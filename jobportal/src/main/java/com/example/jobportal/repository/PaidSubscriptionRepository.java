package com.example.jobportal.repository;

import com.example.jobportal.model.PaidSubscription;
import com.example.jobportal.model.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidSubscriptionRepository extends JpaRepository<PaidSubscription, Long> {

    List<PaidSubscription> findByRecruiterId(Long recruiterId);

    List<PaidSubscription> findByStatus(SubscriptionStatus status);

    long countByPaymentStatus(String paymentStatus);

    long countByStatus(SubscriptionStatus status);

}

