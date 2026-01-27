package com.example.demo.controller;
import java.util.List;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.mapper.AuthorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Author;
import com.example.demo.service.FindAuthorBooksService;
@Slf4j
@RestController
public class AuthorBookController {
	
	// Dependency Injection via Constructor
	private final FindAuthorBooksService findAuthorBooksService;
	private AuthorMapper authorMapper;

	// Constructor
	public AuthorBookController(FindAuthorBooksService findAuthorBooksService, AuthorMapper authorMapper) {
		this.findAuthorBooksService = findAuthorBooksService;
		this.authorMapper = authorMapper;
		this.authorMapper = authorMapper;
	}
	
	// Simple test endpoint to verify the controller is working
	@GetMapping("/api/author")
	public String hello(@RequestParam String name) {

		Author unAuth = findAuthorBooksService.findAuthorByName(name);
		if(unAuth == null){
			log.info("Author non trouvé");
			return "Author non trouvé";
		}else{
			log.info("Authors trouvé!");
			return "Authors trouvé!";
		}

	}
	
	@GetMapping("/api/authors")
	public List<AuthorDto> getAllAuthors() {
		//return findAuthorBooksService.findAllAuthors();
		return authorMapper.toDtoList(findAuthorBooksService.findAllAuthors());
	}
}
