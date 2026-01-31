package com.example.demo.exception;

public class AuthorNotFoundException extends RuntimeException {
	
	 public AuthorNotFoundException(Long authorId) {
	        super("L'author recherche avec l'id: " + authorId + " n'a pas été trouvé.");
	    }
}
