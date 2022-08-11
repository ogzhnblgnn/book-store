package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
}
