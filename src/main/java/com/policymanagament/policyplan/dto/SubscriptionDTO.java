package com.policymanagament.policyplan.dto;

import java.util.Date;

import lombok.Data;
@Data
public class SubscriptionDTO {
    private int subscriptionId;
    private int policyId;
    private Date subscriptionDate;
    private String policyHolderName;
    private String username;
    private String subscriptionStatus;
    private String medicalCertificateDocURL;
    private String relationToPolicyHolder;
    private String policyHolderIDProofType;
    private String policyHolderIDProofNo;

}