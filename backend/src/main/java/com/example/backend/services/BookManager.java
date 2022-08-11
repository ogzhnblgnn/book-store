package com.example.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entities.Book;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.repositories.BookRepository;
import com.example.backend.services.Abstract.BookService;

@Service
public class BookManager implements BookService {

    private final BookRepository bookRepository;

    

    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ApiResponse<List<Book>> getAllBooks() {
       List<Book> books = bookRepository.findAll();
        return ApiResponse.default_OK(books);
    }

    @Override
    public ApiResponse<Book> getBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book is could not found"));
        return ApiResponse.default_OK(book);
    }

    @Override
    public ApiResponse<Book> addBook(Book book) {
        bookRepository.save(book);
        return ApiResponse.default_CREATED(book);
    }

    @Override
    public ApiResponse<Book> updateBook(int id, Book book) {
        getBookById(id);
        book.setId(id);
        bookRepository.save(book);
        return ApiResponse.default_ACCEPTED(book);
    }

    @Override
    public ApiResponse<?> deleteBook(int id) {
        bookRepository.deleteById(id);
        return ApiResponse.default_NO_CONTENT();
    }

}
