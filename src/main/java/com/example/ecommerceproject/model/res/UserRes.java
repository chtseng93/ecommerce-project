package com.example.ecommerceproject.model.res;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerceproject.entity.Role;

import lombok.Data;

@Data
public class UserRes {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String token;
	private Collection<String> roles = new HashSet<>();

	public UserRes(Integer userId, String firstName, String lastName, String email, String token,
			Collection<String> roles) {

		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.token = token;
		this.roles = roles;
	}

	public UserRes(Integer userId, String firstName, String lastName, String email, Collection<String> roles) {
	
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	
	
	
}
