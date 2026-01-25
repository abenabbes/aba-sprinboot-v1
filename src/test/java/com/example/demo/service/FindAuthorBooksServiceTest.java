package com.example.demo.service;

import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public interface FindAuthorBooksServiceTest {

    @Autowired
    private FindAuthorBooksService findAuthorBooksService;

    @Autowired
    private AuthorRepository authorRepository ;
}
