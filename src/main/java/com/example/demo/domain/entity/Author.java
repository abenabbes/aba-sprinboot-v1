/**
 * 
 */
package com.example.demo.domain.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
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
	
	//	constructeur protected → bonne pratique JPA
	protected Author() {
		
	}
	
	//	constructor
	public Author(String name) {
		this.name = name;
	}
	
	//	getter
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Book> getBooks() {
		return books;
	}
	
	
}
