package com.example.demo.domain.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record BookDto(
        Long id,
        @NotBlank(message = "Le titre du livre est obligatoire")
        String titre,
        @NotBlank(message = "Le nombre de page du livre est obligatoire")
        int pages,
        @NotBlank(message = "La date de publication du livre est obligatoire")
        LocalDate datePublication
        
) {
}
