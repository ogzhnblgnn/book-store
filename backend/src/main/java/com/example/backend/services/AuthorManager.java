package com.example.backend.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.backend.entities.Author;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.exceptions.notFoundExceptions.AuthorNotFoundException;
import com.example.backend.repositories.AuthorRepository;
import com.example.backend.services.Abstract.AuthorService;

@Service
public class AuthorManager implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorManager(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public ApiResponse<List<Author>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return ApiResponse.default_OK(authors);
    }

    @Override
    public ApiResponse<Author> getAuthorById(int id) {
        Author author = authorRepository // -> You can use <OPTIONAL> type and you'll see this method returns
                                         // Optional<Author>
                .findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id)); // Cause of the exception: <OPTIONAL> return of
                                                                                     // findById
        return ApiResponse.default_OK(author);
    }

    @Override
    public ApiResponse<Author> addAuthor(Author author) {
        authorRepository.save(author);
        return ApiResponse.default_CREATED(author);
    }

    @Override
    public ApiResponse<Author> updateAuthor(int id, Author author) {
        getAuthorById(id);
        author.setId(id);
        authorRepository.save(author);
        return ApiResponse.default_ACCEPTED(author);
    }

    @Override
    public ApiResponse<?> deleteAuthor(int id) {
        getAuthorById(id);
        authorRepository.deleteById(id);
        return ApiResponse.default_NO_CONTENT();
    }

    @Override
    public Set<Author> getAuthorByIds(List<Integer> authorIds) {
        Set<Author> authors = new HashSet<>();
        authorIds.forEach(id -> authors.add(getAuthorById(id).getData()));
        return authors;
    }

}
