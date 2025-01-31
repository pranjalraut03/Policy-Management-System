package com.policymanagament.policyplan.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int policyId;
    private String policyName;
    private  String policyDescription ;
    private int tenure;
    private double premiumAmount;
    private double maturityAmount;

    @ManyToOne
    @JoinColumn(name = "policyTypeId")
    private PolicyType policyType;


   
}
