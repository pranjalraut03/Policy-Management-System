package com.policymanagament.policyplan.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionId;

    @ManyToOne
    @JoinColumn(name = "policyId")
    private Policy policy;

    @Temporal(TemporalType.DATE) 
    private Date subscriptionDate = new Date();

    @Column(nullable = false, length = 50)
    @Size(min=5, message="Username must be 5 character long")
    private String policyHolderName;

    @Column(length = 6)
    private String username;

    @Column(nullable = false, length = 15)
    private String subscriptionStatus;

    @Column(length = 200)
    private String medicalCertificateDocURL;

    @Column(nullable = false, length = 15)
    private String relationToPolicyHolder;

    @Column(length = 10)
    private String policyHolderIDProofType;

    @Column(length = 12)
    private String policyHolderIDProofNo;

    
}