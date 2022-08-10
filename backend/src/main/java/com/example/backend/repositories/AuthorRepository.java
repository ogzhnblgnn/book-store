package com.example.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    
}
