package com.example.demo.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.demo.domain.entity.Book;
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
    	 Author author1 = new Author("Author One");
         Author author2 = new Author("Author Two");
         authorRepository.save(author1);
         authorRepository.save(author2);

         bookRepository.save(new Book("Book One", author1));
         bookRepository.save(new Book("Book Two", author1));

         List<Author> authors = authorRepository.findAllWithBooks();

         assertThat(authors).hasSize(2);

         Author a1 = authors.stream()
                 .filter(a -> a.getName().equals("Author One"))
                 .findFirst()
                 .orElseThrow();

         assertThat(a1.getBooks()).isNullOrEmpty();

         Author a2 = authors.stream()
                 .filter(a -> a.getName().equals("Author Two"))
                 .findFirst()
                 .orElseThrow();

         assertThat(a2.getBooks()).isNullOrEmpty();
    }

}
