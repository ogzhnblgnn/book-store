package com.example.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Author;
import com.example.backend.services.Abstract.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable(name="id", required = true) int id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable(name="id", required = true) int id, @RequestBody Author author){
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable(name="id", required = true) int id){
        authorService.deleteAuthor(id);
    }
    
}
