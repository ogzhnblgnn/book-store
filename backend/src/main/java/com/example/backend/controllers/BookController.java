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

import com.example.backend.entities.Book;
import com.example.backend.entities.dto.BookDto;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.services.Abstract.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ApiResponse<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ApiResponse<Book> getBookById(@PathVariable(name = "id", required = true) int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('book:post')")
    public ApiResponse<Book> addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('book:put')")
    public ApiResponse<Book> updateBook(@PathVariable(name="id", required = true) int id, @RequestBody BookDto bookDto){
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('book:delete')")
    public ApiResponse<?> deleteBook(@PathVariable(name="id", required = true) int id){
        return bookService.deleteBook(id);
    }

}
