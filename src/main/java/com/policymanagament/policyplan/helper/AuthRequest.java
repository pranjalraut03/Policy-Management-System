package com.policymanagament.policyplan.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AuthRequest {
	private String username;
	private String password;
}