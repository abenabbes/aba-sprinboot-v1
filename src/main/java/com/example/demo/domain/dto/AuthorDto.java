package com.example.demo.domain.dto;

import java.util.List;

import com.example.demo.domain.Gender;

import jakarta.validation.constraints.NotBlank;

/**
 * Ce DTO est un DTO de lecture (output DTO).
 * Il sert à :
 * retourner un Author au client
 * exposer l’état du système
 * inclure éventuellement les Books
 */
public record AuthorDto(
         Long id ,
         @NotBlank(message = "Le prénom de l'auteur est obligatoire")
         String prenom,
         @NotBlank(message = "Le nom de l'auteur est obligatoire")
         String nom,
         @NotBlank(message = "Le sexe de l'auteur est obligatoire")
         Gender sexe,
         List<BookDto> listBooks
) {


}
