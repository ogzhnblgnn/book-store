package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.Author;

public interface AuthorService {
    
    List<Author> getAllAuthors();
    Author getAuthorById(int id);
    Author addAuthor(Author author);
    Author updateAuthor(int id, Author author);
    void deleteAuthor(int id);
}
