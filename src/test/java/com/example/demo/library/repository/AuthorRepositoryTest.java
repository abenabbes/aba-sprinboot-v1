package com.example.demo.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.domain.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void should_return_authors_even_without_books() {
        Author author = new Author("George Orwell");
        authorRepository.save(author);

        List<Author> authors = authorRepository.findAllWithBooks();

        assertThat(authors).isNotNull();
        assertThat(authors).hasSize(1);
        assertThat(authors.get(0).getBooks()).isEmpty();
    }
}
