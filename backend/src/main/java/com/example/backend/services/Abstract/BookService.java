package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.Book;
import com.example.backend.entities.dto.BookDto;
import com.example.backend.entities.models.ApiResponse;

public interface BookService {
    ApiResponse<List<Book>> getAllBooks();
    ApiResponse<Book> getBookById(int id);
    ApiResponse<Book> addBook(BookDto bookDto);
    ApiResponse<Book> updateBook(int id, BookDto bookDto);
    ApiResponse<?> deleteBook(int id);
}
