/**
 * 
 */
package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.dto.CreateAuthorRequest;
import com.example.demo.domain.entity.Author;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class CreateAuthorServiceImpl implements CreateAuthorService {

	// Attribut 
	private final AuthorRepository auAuthorRepository;
	private final AuthorMapper authorMapper;
	
	@Override
	public AuthorDto createAuthor(AuthorDto request) {
		// Créer un Author entité à partir du DTO
		Author authorEntity = authorMapper.toEntity(request);
		// Sauvegarder l'Author en base
		Author savedAuthor = auAuthorRepository.save(authorEntity);
		// Retourner le DTO de l'Author créé
		return authorMapper.toDto(savedAuthor);
	}

}
