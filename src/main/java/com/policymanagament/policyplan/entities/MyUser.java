package com.policymanagament.policyplan.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Entity
@Table(name = "USER100")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MyUser {
	@Id
	private String username;
	private String password;
	private String roles;	
}