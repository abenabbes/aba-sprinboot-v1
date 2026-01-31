package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Gender;
import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.entity.Author;
import com.example.demo.domain.entity.Book;
import com.example.demo.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class FindAuthorBooksServiceTest {

	    @Mock
	    private AuthorRepository authorRepository;

	    @InjectMocks
	    private FindAuthorBooksServiceImpl findAuthorBooksServiceImpl;
	    
	    public void createJDD() {
			// Créer des données de test si nécessaire
		    	 Author author1 = new Author();
			 author1.setNom("Author One");
			 author1.setPrenom("First One");
			 author1.setSexe(Gender.M);
			     
		    Author author2 = new Author();
		    author2.setNom("Author Two");
		    author2.setPrenom("First Two");
		    author2.setSexe(Gender.MME);
		    
	
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
		}
	    
	    //@Test
	    void should_return_authors_from_repository() {
	        // given
	        Author author1 = new Author();
			 author1.setNom("Author One");
			 author1.setPrenom("First One");
			 author1.setSexe(Gender.M);
	        when(authorRepository.findAll()).thenReturn(List.of(author1));

	        // when
//	        List<AuthorDto> authors = findAuthorBooksServiceImpl.findAllAuthors();

	        // then
//	        assertThat(authors).isNull();
	                
	    }
	    
	    @Test
	    void recherche_author_by_name() {
	        // given
	        //String name = "Author Name";
	    	Author author1 = new Author();
			 author1.setNom("Author One");
			 author1.setPrenom("First One");
			 author1.setSexe(Gender.M);
	        when(authorRepository.findAuthorByNom("Author Name")).thenReturn(author1);

	        // when
	        String result = findAuthorBooksServiceImpl.findAuthorByNom("Author Name");

	        // then
	        assertThat(result).isEqualTo("Authors trouvé!");
	    }
	    
	    @Test
	    void recherche_author_by_name_not_found() {
	        // given
	        String name = "Unknown Author";
	        when(authorRepository.findAuthorByNom(name)).thenReturn(null);

	        // when
	        String result = findAuthorBooksServiceImpl.findAuthorByNom(name);

	        // then
	        assertThat(result).isEqualTo("Authors non trouvé");
	    }
	    
	    //@Test
	    void should_find_author_by_id() {
	        // given
	        Long id = 1L;
	        Author author1 = new Author();
			 author1.setNom("Author One");
			 author1.setPrenom("First One");
			 author1.setSexe(Gender.M);
	        when(authorRepository.findById(id)).thenReturn(java.util.Optional.of(author1));

	        // when
	        // AuthorDto authorDto = findAuthorBooksServiceImpl.findAuthorById(id);

	        // then
//	         assertThat(authorDto).isNotNull();
//	         assertThat(authorDto.name()).isEqualTo("Author Name");
	    }
	    
	    
	    
}
