package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
