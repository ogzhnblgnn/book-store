package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.Book;
import com.example.backend.entities.models.ApiResponse;

public interface BookService {
    ApiResponse<List<Book>> getAllBooks();
    ApiResponse<Book> getBookById(int id);
    ApiResponse<Book> addBook(Book book);
    ApiResponse<Book> updateBook(int id, Book book);
    ApiResponse<?> deleteBook(int id);
}
