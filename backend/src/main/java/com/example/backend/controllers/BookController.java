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

import com.example.backend.entities.Book;
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
    public ApiResponse<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ApiResponse<Book> getBookById(@PathVariable(name = "id", required = true) int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public ApiResponse<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public ApiResponse<Book> updateBook(@PathVariable(name="id", required = true) int id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteBook(@PathVariable(name="id", required = true) int id){
        return bookService.deleteBook(id);
    }

}
