package com.example.demo.service;

import com.example.demo.domain.entity.Author;

import java.util.List;

public interface FindAuthorBooksService {

    List<Author> findAllAuthors();
}
