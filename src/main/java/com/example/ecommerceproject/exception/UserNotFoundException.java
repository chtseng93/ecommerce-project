package com.example.ecommerceproject.exception;

public class UserNotFoundException extends RuntimeException {
    
	public  UserNotFoundException(String message) {
        super(message);
    }
}

