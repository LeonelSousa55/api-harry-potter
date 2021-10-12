package com.dextra.api.harry.potter.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dextra.api.harry.potter.services.exceptions.DataBaseException;
import com.dextra.api.harry.potter.services.exceptions.ResourceNotFoundException;
import com.dextra.api.harry.potter.services.exceptions.ValidationFailException;



@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err  =  new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resuorce not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err  =  new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Data base exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ValidationFailException.class)
	public ResponseEntity<StandardError> validation(ValidationFailException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err  =  new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
