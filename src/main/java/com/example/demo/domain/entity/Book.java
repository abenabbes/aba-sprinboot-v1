/**
 * 
 */
package com.example.demo.domain.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table(name = "books")
// Annotation Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private int pages;
    
    @Column(nullable = false)
    private LocalDate datePublication;

    // Plusieurs Books pour un Author
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id") // clé étrangère vers Author
    private Author author;


}
