package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import com.example.demo.domain.dto.CreateAuthorRequest;
import com.example.demo.domain.entity.Author;
import com.example.demo.domain.entity.Book;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class CreateAuthorServiceTest {

	@Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private CreateAuthorServiceImpl service;

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
    void should_create_author() {
        //AuthorDto request = new AuthorDto("George Orwell");

        Author author = new Author();
        author.setNom("Author One");
        author.setPrenom("First One");
        author.setSexe(Gender.M);
        
        
        Author savedAuthor = new Author();
        savedAuthor.setNom("Author One");
        savedAuthor.setPrenom("First One");
        savedAuthor.setSexe(Gender.M);

        AuthorDto dto = new AuthorDto(1L, "George Orwell","George Orwell", Gender.M,List.of());

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);
        when(authorMapper.toDto(savedAuthor)).thenReturn(dto);

        AuthorDto result = service.createAuthor(dto);

        assertThat(result.nom()).isEqualTo("George Orwell");
    }
}
