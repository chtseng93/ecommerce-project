package com.example.ecommerceproject.exception;


public class InternalServerErrorException extends RuntimeException {
    
	public InternalServerErrorException(String message) {
        super(message);
    }
}
