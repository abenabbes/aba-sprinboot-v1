package com.example.demo.service;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.dto.CreateAuthorRequest;

public interface CreateAuthorService {

	// Create a new Author : POST /authors
	AuthorDto createAuthor(AuthorDto request);
}
