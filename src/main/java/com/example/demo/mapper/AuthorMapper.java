package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entity.Author;
import com.example.demo.domain.entity.Book;

@Component
public class AuthorMapper {

    /* =========================
    Entity → DTO
    ========================= */

	 public AuthorDto toDto(Author entity) {
	     if (entity == null) {
	         return null;
	     }
	
	     return new AuthorDto(
	    		 entity.getId(),
	    		 entity.getPrenom(),
	    		 entity.getNom(),
	    		 entity.getSexe(),
	    		 	toBookDtoList(entity.getBooks())
	     );
	 }
	
	 public List<AuthorDto> toDtoList(List<Author> listEntitys) {
	     return listEntitys == null
	             ? List.of()
	             : listEntitys.stream()
	                      .map(this::toDto)
	                      .toList();
	 }
	
	 /* =========================
	    DTO → Entity
	    (création Author)
	    ========================= */
	
	 public Author toEntity(AuthorDto dto) {
	     if (dto == null) {
	         return null;
	     }
	
	     return Author.builder()
	             .prenom(dto.prenom())
	             .nom(dto.nom())
	             .sexe(dto.sexe())
	             .build();
	 }
	
	 /* =========================
	    Book mapping
	    ========================= */
	
	 private BookDto toBookDto(Book entity) {
	     return new BookDto(
	    		 entity.getId(),
	    		 entity.getTitle(),
	    		 entity.getPages(),
	    		 entity.getDatePublication()
	     );
	 }
	
	 private List<BookDto> toBookDtoList(List<Book> listEntitys) {
	     return listEntitys == null
	             ? List.of()
	             : listEntitys.stream()
	                    .map(this::toBookDto)
	                    .toList();
	 }
}
