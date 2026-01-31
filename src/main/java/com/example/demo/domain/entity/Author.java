/**
 * 
 */
package com.example.demo.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table(name = "authors")
// Annotation Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)	
	private String nom;
	
	@Column(nullable = false)
	private String prenom;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender sexe;
	
	// Un Author pour plusieurs Books
	@OneToMany(
			mappedBy = "author",
			fetch = FetchType.LAZY, // LAZY partout par défaut (évite 80% des bugs de perf)
								 // chargement différé des Books d'un Author
			                     // (utile si on n'a pas toujours besoin des Books)
			                     // Par défaut, c'est FetchType.EAGER pour @OneToMany
			cascade = CascadeType.ALL,
			orphanRemoval = true // orphanRemoval évite les incohérences : si un Book n'est plus rattaché à un Author, il est supprimé de la BDD
			)
	private List<Book> books;
	
	
}
