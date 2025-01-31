package com.policymanagament.policyplan.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanagament.policyplan.dto.PolicyDTO;
import com.policymanagament.policyplan.dto.PolicyTypeDTO;
import com.policymanagament.policyplan.dto.SubscriptionDTO;
import com.policymanagament.policyplan.exception.PolicyLimitReachedException;
import com.policymanagament.policyplan.service.PolicyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/types")
    @PreAuthorize("hasAuthority('MANAGER')")
	@SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<PolicyTypeDTO>> getAllPolicyTypes(Authentication authentication) {
        return ResponseEntity.ok(policyService.getAllPolicyTypes());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
	@SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PolicyDTO> createPolicy(@RequestBody PolicyDTO policy,Authentication authentication) {
    	System.out.println(policy);
        return ResponseEntity.status(201).body(policyService.createPolicy(policy));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
	@SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable Integer id,Authentication authentication) {
        return ResponseEntity.ok(policyService.getPolicyById(id));
    }
    @GetMapping("/search/{policyName}") 
    @PreAuthorize("hasAuthority('CUSTOMER')")
	@SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PolicyDTO> getPolicyByName(@PathVariable String policyName,Authentication authentication) { 
    	return ResponseEntity.ok(policyService.getPolicyByPolicyName(policyName)); }

    @PostMapping("/subscriptions")
    @PreAuthorize("hasAuthority('CUSTOMER')")
	@SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SubscriptionDTO> addSubscription(@RequestBody SubscriptionDTO subscription,Authentication authentication) {
        try {
            return ResponseEntity.status(201).body(policyService.addSubscription(subscription));
        } catch (PolicyLimitReachedException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}