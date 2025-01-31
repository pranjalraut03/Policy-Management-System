package com.policymanagament.policyplan.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PolicyType {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int policyTypeId;
	    private String policyTypeName;
	    
	
	    
	}


