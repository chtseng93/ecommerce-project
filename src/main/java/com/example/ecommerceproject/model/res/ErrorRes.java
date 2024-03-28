package com.example.ecommerceproject.model.res;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ErrorRes {
	
	HttpStatus httpStatus;
    String message;

    public ErrorRes(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }


}
