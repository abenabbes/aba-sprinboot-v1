package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.entity.Author;

public interface FindAuthorBooksService {

    List<AuthorDto> findAllAuthors();

    //Recherche un seule author avec son name
    String findAuthorByNom(String nom);
    //Recherche un seule author avec son id
    AuthorDto findAuthorById(Long id);
}
