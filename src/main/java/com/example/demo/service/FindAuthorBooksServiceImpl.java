package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.entity.Author;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorRepository;

@Service
public class FindAuthorBooksServiceImpl implements FindAuthorBooksService{

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public FindAuthorBooksServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public List<AuthorDto> findAllAuthors() {
        return authorMapper.toDtoList(authorRepository.findAll());
    }

    @Override
    public String findAuthorByNom(String nom) {
        // Logique métier
        // Vérifier d'abord si un Author existe d'abord
    	if(authorRepository.findAuthorByNom(nom) == null){
    		
			return "Authors non trouvé";
		}else {
			
			return "Authors trouvé!";
		}
    }


	@Override
	public AuthorDto findAuthorById(Long id) {
		Author author = authorRepository.findById(id)
	            .orElseThrow(() -> new AuthorNotFoundException(id));		
		return authorMapper.toDto(author);
	}
}
