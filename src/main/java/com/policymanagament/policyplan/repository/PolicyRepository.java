package com.policymanagament.policyplan.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policymanagament.policyplan.entities.Policy;
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
	Optional<Policy> findByPolicyName(String policyName);
}
