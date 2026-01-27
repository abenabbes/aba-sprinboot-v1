package com.example.demo.mapper;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entity.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {

    // Mapper Entité vers DTO
    public AuthorDto entiteToDto(Author entite){
        return new AuthorDto(
                entite.getId(),
                entite.getName(),
                entite.getBooks() == null
                        ? List.of()
                        : entite.getBooks().stream()
                        .map(book -> new BookDto(book.getId(), book.getTitle()))
                        .toList()
        );
    }

    public List<AuthorDto> toDtoList(List<Author> authors) {
        return authors.stream()
                .map(this::entiteToDto)
                .toList();
    }


}
