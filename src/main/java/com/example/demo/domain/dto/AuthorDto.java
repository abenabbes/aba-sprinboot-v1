package com.example.demo.domain.dto;

import java.util.List;

public record AuthorDto(
         Long id ,
         String name,
         List<BookDto> listBooks
) {


}
