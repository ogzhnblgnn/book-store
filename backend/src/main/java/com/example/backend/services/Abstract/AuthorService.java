package com.example.backend.services.Abstract;

import java.util.List;
import java.util.Set;

import com.example.backend.entities.Author;
import com.example.backend.entities.models.ApiResponse;

public interface AuthorService {
    
    ApiResponse<List<Author>> getAllAuthors();
    ApiResponse<Author> getAuthorById(int id);
    ApiResponse<Author> addAuthor(Author author);
    ApiResponse<Author> updateAuthor(int id, Author author);
    ApiResponse<?> deleteAuthor(int id);
    Set<Author> getAuthorByIds(List<Integer> authorIds);
}
