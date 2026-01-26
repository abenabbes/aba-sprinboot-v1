package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.entity.Author;
import com.example.demo.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class FindAuthorBooksServiceTest {

	    @Mock
	    private AuthorRepository authorRepository;

	    @InjectMocks
	    private FindAuthorBooksServiceImpl findAuthorBooksServiceImpl;
	    
	    @Test
	    void should_return_authors_from_repository() {
	        // given
	        Author author = new Author("Author Name");
	        when(authorRepository.findAll()).thenReturn(List.of(author));

	        // when
	        List<Author> authors = findAuthorBooksServiceImpl.findAllAuthors();

	        // then
	        assertThat(authors)
	                .isNotNull()
	                .hasSize(1);
	    }
}
