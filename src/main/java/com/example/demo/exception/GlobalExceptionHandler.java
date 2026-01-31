package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.domain.dto.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	    @ExceptionHandler(AuthorNotFoundException.class)
	    public ResponseEntity<ApiError> handleAuthorNotFound(AuthorNotFoundException ex) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(new ApiError(ex.getMessage()));
	    }
	    
	 
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors()
	                .forEach(error ->
	                        errors.put(error.getField(), error.getDefaultMessage())
	                );

	        return errors;
	    }

}
