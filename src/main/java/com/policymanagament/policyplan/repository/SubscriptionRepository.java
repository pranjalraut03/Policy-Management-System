package com.policymanagament.policyplan.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.policymanagament.policyplan.entities.Subscription;
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
