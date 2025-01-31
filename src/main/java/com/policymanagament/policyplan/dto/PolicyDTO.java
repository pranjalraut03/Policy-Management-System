package com.policymanagament.policyplan.dto;

import lombok.Data;

@Data
public class PolicyDTO {
    private int policyId;
    private String policyName;
    private String policyDescription;
    private int tenure;
    private double premiumAmount;
    private double maturityAmount;
    private int policyTypeId;   
}