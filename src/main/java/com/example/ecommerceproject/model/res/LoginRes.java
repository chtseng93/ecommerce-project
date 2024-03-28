package com.example.ecommerceproject.model.res;

import lombok.Data;

@Data
public class LoginRes {
	
	private String email;
	private String token;

	public LoginRes(String email, String token) {
		this.email = email;
		this.token = token;
	}

}
