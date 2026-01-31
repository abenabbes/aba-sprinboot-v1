package com.example.demo.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.service.CreateAuthorService;
import com.example.demo.service.FindAuthorBooksService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthorBookController {
	
	// Dependency Injection via Constructor
	private final FindAuthorBooksService findAuthorBooksService;
	private AuthorMapper authorMapper;
	private CreateAuthorService createAuthorService;

	// Constructor
	public AuthorBookController(FindAuthorBooksService findAuthorBooksService, AuthorMapper authorMapper, CreateAuthorService createAuthorService) {
		this.findAuthorBooksService = findAuthorBooksService;
		this.authorMapper = authorMapper;
		this.createAuthorService = createAuthorService;
	}
	
	// Simple test endpoint to verify the controller is working
	@GetMapping("/api/author")
	public String getAuthorByNom(@RequestParam String nom) {
		log.debug("Critère de recherche un Nom : " ,nom);
		return findAuthorBooksService.findAuthorByNom(nom);

	}
	
	@GetMapping("/api/authors")
	public List<AuthorDto> getAllAuthors() {
		return findAuthorBooksService.findAllAuthors();
	}
	
	@GetMapping("/api/author/{id}")
	public AuthorDto getAuthorById(@PathVariable Long id) {
	    return findAuthorBooksService.findAuthorById(id);
	
	}
	
	// Other endpoints for creating authors, updating authors, etc. can be added here
	@PostMapping("/api/createAuthor")
	@ResponseStatus(HttpStatus.CREATED)
	public AuthorDto getCreateAuthorService(
			@Valid       // Le champ doit être validé avant d'entrer dans la méthode
			@RequestBody // Le corps de la requête HTTP est mappé à l'objet AuthorDto
			AuthorDto request) {
		
		return createAuthorService.createAuthor(request);
	}
}
