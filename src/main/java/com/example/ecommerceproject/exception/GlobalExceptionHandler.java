package com.example.ecommerceproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(value = {ResourceNotFoundException.class,UserNotFoundException.class})
	public ResponseEntity notFoundexception(RuntimeException exception) {
		String message = exception.getMessage();
		return new ResponseEntity(message, HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(value = {InternalServerErrorException.class})
	public ResponseEntity internalServerexception(RuntimeException exception) {
		String message = exception.getMessage();
		return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(value = {UserRoleAlreadyExistException.class,UserAlreadyExistsException.class})
	public ResponseEntity dataAlreadyExistsServerexception(RuntimeException exception) {
		String message = exception.getMessage();
		return new ResponseEntity(message, HttpStatus.CREATED);
	
	}
		

}
