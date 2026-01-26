package com.example.demo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Author;
import com.example.demo.service.FindAuthorBooksService;

@RestController
public class AuthorBookController {
	
	// Dependency Injection via Constructor
	private final FindAuthorBooksService findAuthorBooksService;
	// Constructor
	public AuthorBookController(FindAuthorBooksService findAuthorBooksService) {
		this.findAuthorBooksService = findAuthorBooksService;
	}
	
	// Simple test endpoint to verify the controller is working
	@GetMapping("/hello")
	public String hello() {
		return "Hello, AuthorBookController is working!";
	}
	
	@GetMapping("/api/authors")
	public List<Author> getAllAuthors() {
		return findAuthorBooksService.findAllAuthors();
	}
}
