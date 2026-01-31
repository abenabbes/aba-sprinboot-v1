package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.domain.Gender;
import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.entity.Author;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.service.CreateAuthorService;
import com.example.demo.service.FindAuthorBooksService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorBookController.class)
@Import(AuthorMapper.class)
public class AuthorControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindAuthorBooksService findAuthorsWithBooksService;
    
    @MockBean
    private AuthorMapper authorMapper;
    
    @MockBean
    private CreateAuthorService createAuthorService;	
    
    //@Test
    void should_return_authors() throws Exception {
        Author author = Author.builder().nom("George Orwell").build();
        AuthorDto authorDto = authorMapper.toDto(author);
        when(findAuthorsWithBooksService.findAllAuthors()).thenReturn(List.of(authorDto));

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("George Orwell"));
    }
    
    @Test
    void should_return_404_when_author_not_found() throws Exception {

        when(findAuthorsWithBooksService.findAuthorById(99L))
                .thenThrow(new AuthorNotFoundException(99L));

        mockMvc.perform(get("/api/author/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("L'author recherche avec l'id: 99 n'a pas été trouvé."));
    }
    
    @Test
    void should_return_list_of_authors() throws Exception {
        // given
//        AuthorDto authorDto = new AuthorDto(
//                1L,
//				"Author Name",
//				List.of()
//        );
    	AuthorDto authorDto = new AuthorDto(
    			1L, 
    			"Author Prenom",
    			"Author Name",
    			Gender.M, 
    			List.of());

        when(findAuthorsWithBooksService.findAllAuthors())
                .thenReturn(List.of(authorDto));

        // when + then
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Author Name"));
    }
    
    @Test
    void should_create_author() throws Exception {
        AuthorDto input = new AuthorDto(null, "Victor Hugo", "Victor Hugo", Gender.M, List.of());
        AuthorDto output = new AuthorDto(1L, "Victor Hugo", "Victor Hugo", Gender.M, List.of());

        when(createAuthorService.createAuthor(any())).thenReturn(output);

        mockMvc.perform(post("/api/createAuthor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Victor Hugo"
                    }
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Victor Hugo"));
    }
    
    @Test
    void should_return_400_when_name_is_blank() throws Exception {
        mockMvc.perform(post("/api/createAuthor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": ""
                    }
                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name")
                        .value("Le nom de l'auteur est obligatoire"));
    }


}
