package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.domain.Gender;
import com.example.demo.domain.entity.Author;
import com.example.demo.domain.entity.Book;

@DataJpaTest
// Cette annotation permet
//  1) démarre uniquement JPA
//  2) configure une base H2 en mémoire
//  3) rollback après chaque test (base propre à chaque fois)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void should_return_authors_even_without_books() {
    	     Author author1 = new Author();
    	     author1.setNom("Author One");
    	     author1.setPrenom("First One");
    	     author1.setSexe(Gender.M);
    	     
         Author author2 = new Author();
         author2.setNom("Author Two");
         author2.setPrenom("First Two");
         author2.setSexe(Gender.MME);
         
         authorRepository.save(author1);
         authorRepository.save(author2);

         Book book1 = new Book();
         book1.setTitle("Book One");
         book1.setPages(100);
         book1.setDatePublication(LocalDate.now());
         book1.setAuthor(author1);
         
         Book book2 = new Book();
         book2.setTitle("Book Two");
         book2.setPages(200);
         book2.setDatePublication(LocalDate.now());
         book2.setAuthor(author2);
         
         bookRepository.save(book1);
         bookRepository.save(book2);

         List<Author> authors = authorRepository.findAllWithBooks();

         assertThat(authors).hasSize(2);

         Author a1 = authors.stream()
                 .filter(a -> a.getNom().equals("Author One"))
                 .findFirst()
                 .orElseThrow();

         assertThat(a1.getBooks()).isNullOrEmpty();

         Author a2 = authors.stream()
                 .filter(a -> a.getNom().equals("Author Two"))
                 .findFirst()
                 .orElseThrow();

         assertThat(a2.getBooks()).isNullOrEmpty();
    }
    
    @Test
    void should_find_author_with_books() {
        // given
    	     Author author1 = new Author();
 	     author1.setNom("Author One");
 	     author1.setPrenom("First One");
 	     author1.setSexe(Gender.M);
        
 	     authorRepository.save(author1);

 	    Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setPages(100);
        book1.setDatePublication(LocalDate.now());
        book1.setAuthor(author1);
        
        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setPages(200);
        book2.setDatePublication(LocalDate.now());
        book2.setAuthor(author1);
        
        author1.setBooks(List.of(book1, book2));

        bookRepository.saveAll(List.of(book1, book2));

        // when
        Author found = authorRepository.findById(author1.getId()).orElseThrow();

        // then
        assertThat(found.getBooks())
                .isNotNull()
                .hasSize(2)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Harry Potter 1", "Harry Potter 2");
    }


}
