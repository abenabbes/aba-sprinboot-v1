package com.example.demo.service;

import com.example.demo.domain.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAuthorBooksServiceImpl implements FindAuthorBooksService{

    private final AuthorRepository authorRepository;

    public FindAuthorBooksServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorByName(String name) {
        // Logique métier
        // Vérifier d'abord si un Author existe d'abord

        return authorRepository.findAuthorByName(name);
    }
}
