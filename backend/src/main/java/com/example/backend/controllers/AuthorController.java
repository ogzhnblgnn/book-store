package com.example.backend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Author;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.services.Abstract.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ApiResponse<List<Author>> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ApiResponse<Author> getAuthorById(@PathVariable(name="id", required = true) int id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('author:post')")
    public ApiResponse<Author> addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('author:put')")
    public ApiResponse<Author> updateAuthor(@PathVariable(name="id", required = true) int id, @RequestBody Author author){
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('author:delete')")
    public ApiResponse<?> deleteAuthor(@PathVariable(name="id", required = true) int id){
        return authorService.deleteAuthor(id);
    }
    
}
