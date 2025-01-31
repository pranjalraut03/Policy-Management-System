package com.policymanagament.policyplan.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.policymanagament.policyplan.dto.PolicyDTO;
import com.policymanagament.policyplan.dto.SubscriptionDTO;
import com.policymanagament.policyplan.entities.Policy;
import com.policymanagament.policyplan.entities.PolicyType;
import com.policymanagament.policyplan.entities.Subscription;
import com.policymanagament.policyplan.exception.PolicyLimitReachedException;
import com.policymanagament.policyplan.repository.PolicyRepository;
import com.policymanagament.policyplan.repository.PolicyTypeRepository;
import com.policymanagament.policyplan.repository.SubscriptionRepository;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {

    @Mock
    private PolicyRepository policyRepository;

    @Mock
    private PolicyTypeRepository policyTypeRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private PolicyService policyService;

    private Policy policy;
    private PolicyDTO policyDTO;
    private PolicyType policyType;
    private Subscription subscription;
    private SubscriptionDTO subscriptionDTO;

    @BeforeEach
    void setUp() {
        // Initialize sample data
        policyType = new PolicyType();
        policyType.setPolicyTypeId(1);
        policyType.setPolicyTypeName("Life Insurance");

        policy = new Policy();
        policy.setPolicyId(1);
        policy.setPolicyName("Life Cover");
        policy.setPolicyDescription("Life Insurance Policy");
        policy.setTenure(10);
        policy.setPremiumAmount(1000.0);
        policy.setPolicyType(policyType);

        policyDTO = new PolicyDTO();
        policyDTO.setPolicyId(1);
        policyDTO.setPolicyName("Life Cover");
        policyDTO.setPolicyDescription("Life Insurance Policy");
        policyDTO.setTenure(10);
        policyDTO.setPremiumAmount(1000.0);
        policyDTO.setPolicyTypeId(1);

        subscription = new Subscription();
        subscription.setSubscriptionId(1);
        subscription.setPolicy(policy);
        subscription.setSubscriptionDate(new Date());
        subscription.setPolicyHolderName("John Doe");
        subscription.setUsername("johndoe");
        subscription.setSubscriptionStatus("Active");

        subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId(1);
        subscriptionDTO.setPolicyId(1);
        subscriptionDTO.setSubscriptionDate(new Date());
        subscriptionDTO.setPolicyHolderName("John Doe");
        subscriptionDTO.setUsername("johndoe");
        subscriptionDTO.setSubscriptionStatus("Active");
    }

    @Test
    void testGetAllPolicyTypes() {
        when(policyTypeRepository.findAll()).thenReturn(Arrays.asList(policyType));

        var result = policyService.getAllPolicyTypes();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Life Insurance", result.get(0).getPolicyTypeName());
    }

    @Test
    void testCreatePolicy() {
        when(policyTypeRepository.findById(1)).thenReturn(Optional.of(policyType));
        when(policyRepository.save(any(Policy.class))).thenReturn(policy);

        var result = policyService.createPolicy(policyDTO);

        assertNotNull(result);
        assertEquals(1, result.getPolicyId());
        assertEquals("Life Cover", result.getPolicyName());
    }

    @Test
    void testGetPolicyById() {
        when(policyRepository.findById(1)).thenReturn(Optional.of(policy));

        var result = policyService.getPolicyById(1);

        assertNotNull(result);
        assertEquals(1, result.getPolicyId());
        assertEquals("Life Cover", result.getPolicyName());
    }

    @Test
    void testAddSubscription() throws PolicyLimitReachedException {
        when(policyRepository.findById(1)).thenReturn(Optional.of(policy));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        var result = policyService.addSubscription(subscriptionDTO);

        assertNotNull(result);
        assertEquals(1, result.getSubscriptionId());
        assertEquals("John Doe", result.getPolicyHolderName());
    }

    // Additional tests for other methods and business logic can be added here
}
