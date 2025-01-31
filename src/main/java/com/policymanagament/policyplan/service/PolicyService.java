package com.policymanagament.policyplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanagament.policyplan.dto.PolicyDTO;
import com.policymanagament.policyplan.dto.PolicyTypeDTO;
import com.policymanagament.policyplan.dto.SubscriptionDTO;
import com.policymanagament.policyplan.entities.Policy;
import com.policymanagament.policyplan.entities.PolicyType;
import com.policymanagament.policyplan.entities.Subscription;
import com.policymanagament.policyplan.exception.PolicyLimitReachedException;
import com.policymanagament.policyplan.repository.PolicyRepository;
import com.policymanagament.policyplan.repository.PolicyTypeRepository;
import com.policymanagament.policyplan.repository.SubscriptionRepository;

import java.util.stream.Collectors;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyTypeRepository policyTypeRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<PolicyTypeDTO> getAllPolicyTypes() {
        return policyTypeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PolicyDTO createPolicy(PolicyDTO policyDTO) {
        Policy policy = convertToEntity(policyDTO);
        policy.setMaturityAmount(calculateMaturityAmount(policy));
        Policy savedPolicy = policyRepository.save(policy);
        return convertToDTO(savedPolicy);
    }

    public List<PolicyDTO> searchPolicies(Integer tenure, Double premiumAmount, Double maturityAmount) {
        // Implement search logic here
        return policyRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PolicyDTO getPolicyById(Integer policyId) {
        return policyRepository.findById(policyId).map(this::convertToDTO).orElse(null);
    }

    public PolicyDTO getPolicyByPolicyName(String policyName) { 
        return policyRepository.findByPolicyName(policyName).map(this::convertToDTO).orElse(null); }
    
    public SubscriptionDTO addSubscription(SubscriptionDTO subscription2) throws PolicyLimitReachedException {
        Subscription subscription = convertToEntity(subscription2);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return convertToDTO(savedSubscription);
    }

    private PolicyTypeDTO convertToDTO(PolicyType policyType) {
        PolicyTypeDTO dto = new PolicyTypeDTO();
        dto.setPolicyTypeId(policyType.getPolicyTypeId());
        dto.setPolicyTypeName(policyType.getPolicyTypeName());
        return dto;
    }

    private PolicyDTO convertToDTO(Policy policy) {
        PolicyDTO dto = new PolicyDTO();
        dto.setPolicyId(policy.getPolicyId());
        dto.setPolicyName(policy.getPolicyName());
        dto.setPolicyDescription(policy.getPolicyDescription());
        dto.setTenure(policy.getTenure());
        dto.setPremiumAmount(policy.getPremiumAmount());
        dto.setMaturityAmount(policy.getMaturityAmount());
        dto.setPolicyTypeId(policy.getPolicyType().getPolicyTypeId());
        return dto;
    }

    private SubscriptionDTO convertToDTO(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setSubscriptionId(subscription.getSubscriptionId());
        dto.setPolicyId(subscription.getPolicy().getPolicyId());
        dto.setSubscriptionDate(subscription.getSubscriptionDate());
        dto.setPolicyHolderName(subscription.getPolicyHolderName());
        dto.setUsername(subscription.getUsername());
        dto.setSubscriptionStatus(subscription.getSubscriptionStatus());
        dto.setMedicalCertificateDocURL(subscription.getMedicalCertificateDocURL());
        dto.setRelationToPolicyHolder(subscription.getRelationToPolicyHolder());
        dto.setPolicyHolderIDProofType(subscription.getPolicyHolderIDProofType());
        dto.setPolicyHolderIDProofNo(subscription.getPolicyHolderIDProofNo());
        return dto;
    }

    private Policy convertToEntity(PolicyDTO policy2) {
        Policy policy = new Policy();
        policy.setPolicyName(policy2.getPolicyName());
        policy.setPolicyDescription(policy2.getPolicyDescription());
        policy.setTenure(policy2.getTenure());
        policy.setPremiumAmount(policy2.getPremiumAmount());
        policy.setPolicyType(policyTypeRepository.findById(policy2.getPolicyTypeId()).orElse(null));
        return policy;
    }

    private Subscription convertToEntity(SubscriptionDTO dto) {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(dto.getSubscriptionId());
        subscription.setPolicy(policyRepository.findById(dto.getPolicyId()).orElse(null));
        subscription.setSubscriptionDate(dto.getSubscriptionDate());
        subscription.setPolicyHolderName(dto.getPolicyHolderName());
        subscription.setUsername(dto.getUsername());
        subscription.setSubscriptionStatus(dto.getSubscriptionStatus());
        subscription.setMedicalCertificateDocURL(dto.getMedicalCertificateDocURL());
        subscription.setRelationToPolicyHolder(dto.getRelationToPolicyHolder());
        subscription.setPolicyHolderIDProofType(dto.getPolicyHolderIDProofType());
        subscription.setPolicyHolderIDProofNo(dto.getPolicyHolderIDProofNo());
        return subscription;
    }

    private double calculateMaturityAmount(Policy policy) {
        double premiumAmount = policy.getPremiumAmount();
        double maturityAmount = 0;
        String policyType = policy.getPolicyType().getPolicyTypeName();
        
        if ("Life Insurance".equals(policyType)) {
            maturityAmount = premiumAmount + (premiumAmount * 0.10);
        } else if ("Health Insurance".equals(policyType)) {
            if (policy.getTenure() <= 10) {
                maturityAmount = premiumAmount + (premiumAmount * 0.08);
            } else {
                maturityAmount = premiumAmount + (premiumAmount * 0.09);
            }
        }
        
        return maturityAmount;
    }
}

//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.policymanagament.policyplan.dto.PolicyDTO;
//import com.policymanagament.policyplan.dto.PolicyTypeDTO;
//import com.policymanagament.policyplan.dto.SubscriptionDTO;
//import com.policymanagament.policyplan.entities.Policy;
//import com.policymanagament.policyplan.entities.PolicyType;
//import com.policymanagament.policyplan.entities.Subscription;
//import com.policymanagament.policyplan.exception.PolicyLimitReachedException;
//import com.policymanagament.policyplan.repository.PolicyRepository;
//import com.policymanagament.policyplan.repository.PolicyTypeRepository;
//import com.policymanagament.policyplan.repository.SubscriptionRepository;
//
//import java.util.stream.Collectors;
//
//@Service
//public class PolicyService {
//
//    @Autowired
//    private PolicyRepository policyRepository;
//
//    @Autowired
//    private PolicyTypeRepository policyTypeRepository;
//
//    @Autowired
//    private SubscriptionRepository subscriptionRepository;
//
//    public List<PolicyTypeDTO> getAllPolicyTypes() {
//        return policyTypeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
//    }
//
//    public PolicyDTO createPolicy(PolicyDTO policyDTO) {
//        Policy policy = convertToEntity(policyDTO);
//        System.out.println(policy.getPolicyId());
//        Policy savedPolicy = policyRepository.save(policy);
//        return convertToDTO(savedPolicy);
//    }
//
//    public List<PolicyDTO> searchPolicies(Integer tenure, Double premiumAmount, Double maturityAmount) {
//        // Implement search logic here
//        return policyRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
//    }
//
//    public PolicyDTO getPolicyById(Integer policyId) {
//        return policyRepository.findById(policyId).map(this::convertToDTO).orElse(null);
//    }
//
//    public PolicyDTO getPolicyByPolicyName(String policyName) { 
//    	return policyRepository.findByPolicyName(policyName).map(this::convertToDTO).orElse(null); }
//    
//    public SubscriptionDTO addSubscription(SubscriptionDTO subscription2) throws PolicyLimitReachedException {
//        Subscription subscription = convertToEntity(subscription2);
//        Subscription savedSubscription = subscriptionRepository.save(subscription);
//        return convertToDTO(savedSubscription);
//    }
//
//    private PolicyTypeDTO convertToDTO(PolicyType policyType) {
//        PolicyTypeDTO dto = new PolicyTypeDTO();
//        dto.setPolicyTypeId(policyType.getPolicyTypeId());
//        dto.setPolicyTypeName(policyType.getPolicyTypeName());
//        return dto;
//    }
//
//    private PolicyDTO convertToDTO(Policy policy) {
//        PolicyDTO dto = new PolicyDTO();
//        dto.setPolicyId(policy.getPolicyId());
//        dto.setPolicyName(policy.getPolicyName());
//        dto.setPolicyDescription(policy.getPolicyDescription());
//        dto.setTenure(policy.getTenure());
//        dto.setPremiumAmount(policy.getPremiumAmount());
//        dto.setMaturityAmount(policy.getMaturityAmount());
//        dto.setPolicyTypeId(policy.getPolicyType().getPolicyTypeId());
//        return dto;
//    }
//
//    private SubscriptionDTO convertToDTO(Subscription subscription) {
//        SubscriptionDTO dto = new SubscriptionDTO();
//        dto.setSubscriptionId(subscription.getSubscriptionId());
//        dto.setPolicyId(subscription.getPolicy().getPolicyId());
//        dto.setSubscriptionDate(subscription.getSubscriptionDate());
//        dto.setPolicyHolderName(subscription.getPolicyHolderName());
//        dto.setUsername(subscription.getUsername());
//        dto.setSubscriptionStatus(subscription.getSubscriptionStatus());
//        dto.setMedicalCertificateDocURL(subscription.getMedicalCertificateDocURL());
//        dto.setRelationToPolicyHolder(subscription.getRelationToPolicyHolder());
//        dto.setPolicyHolderIDProofType(subscription.getPolicyHolderIDProofType());
//        dto.setPolicyHolderIDProofNo(subscription.getPolicyHolderIDProofNo());
//        return dto;
//    }
//
//    private Policy convertToEntity(PolicyDTO policy2) {
//        Policy policy = new Policy();
////        policy.setPolicyId(policy2.getPolicyTypeId());
//        policy.setPolicyName(policy2.getPolicyName());
//        policy.setPolicyDescription(policy2.getPolicyDescription());
//        policy.setTenure(policy2.getTenure());
//        policy.setPremiumAmount(policy2.getPremiumAmount());
//        policy.setMaturityAmount(policy2.getMaturityAmount());
//        policy.setPolicyType(policyTypeRepository.findById(policy2.getPolicyTypeId()).orElse(null));
//        return policy;
//    }
//
//    private Subscription convertToEntity(SubscriptionDTO dto) {
//        Subscription subscription = new Subscription();
//        subscription.setSubscriptionId(dto.getSubscriptionId());
//        subscription.setPolicy(policyRepository.findById(dto.getPolicyId()).orElse(null));
//        subscription.setSubscriptionDate(dto.getSubscriptionDate());
//        subscription.setPolicyHolderName(dto.getPolicyHolderName());
//        subscription.setUsername(dto.getUsername());
//        subscription.setSubscriptionStatus(dto.getSubscriptionStatus());
//        subscription.setMedicalCertificateDocURL(dto.getMedicalCertificateDocURL());
//        subscription.setRelationToPolicyHolder(dto.getRelationToPolicyHolder());
//        subscription.setPolicyHolderIDProofType(dto.getPolicyHolderIDProofType());
//        subscription.setPolicyHolderIDProofNo(dto.getPolicyHolderIDProofNo());
//        return subscription;
//    }
//
//}