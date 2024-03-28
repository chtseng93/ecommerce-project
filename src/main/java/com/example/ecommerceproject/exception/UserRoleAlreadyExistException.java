package com.example.ecommerceproject.exception;



public class UserRoleAlreadyExistException extends RuntimeException {
    public UserRoleAlreadyExistException(String message) {
        super(message);
    }
}
