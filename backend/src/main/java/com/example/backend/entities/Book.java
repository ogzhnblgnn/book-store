package com.example.backend.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name="book_id")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    

    @ManyToMany
    @JoinTable(name="bookauthors", joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns= @JoinColumn(name="auhtor_id"))
    private Set<Author> authors;
}
