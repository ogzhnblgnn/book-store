package com.example.backend.services;

import java.util.List;

import com.example.backend.entities.Author;
import com.example.backend.repositories.AuthorRepository;
import com.example.backend.services.Abstract.AuthorService;

public class AuthorManager implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorManager(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    @Override
    public Author getAuthorById(int id) {
        Author author = authorRepository // -> You can use <OPTIONAL> type and you'll see this method returns
                                         // Optional<Author>
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Author could not found!")); // Cause of <OPTIONAL> return of
                                                                                     // findById
        return author;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(int id, Author author) {
        getAuthorById(id);
        author.setId(id);
        authorRepository.save(author);
        return author;
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

}
