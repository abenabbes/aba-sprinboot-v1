package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	// recherche par book
	// Explication :
	
	// LEFT JOIN
	//   → retourne tous les auteurs, même ceux sans livres
	
	// FETCH
	// FETCH → force le chargement immédiat
	//   → évite le N+1 problem
	//   → Hibernate charge authors + books en une requête
	
	// DISTINCT → évite les doublons d’auteurs
	@Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books")
	List<Author> findAllWithBooks();

	Author findAuthorByNom(String nom);
	
	//Optional<Author> findAuthorById(Long id);
}
