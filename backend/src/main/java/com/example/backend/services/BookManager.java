package com.example.backend.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.backend.entities.Author;
import com.example.backend.entities.Book;
import com.example.backend.entities.Category;
import com.example.backend.entities.dto.BookDto;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.repositories.BookRepository;
import com.example.backend.services.Abstract.AuthorService;
import com.example.backend.services.Abstract.BookService;
import com.example.backend.services.Abstract.CategoryService;

@Service
public class BookManager implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    

    public BookManager(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
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
    public ApiResponse<Book> addBook(BookDto bookDto) {
        Category category = categoryService.getCategoryById(bookDto.getCategoryId()).getData();
        Set<Author> authors = authorService.getAuthorByIds(bookDto.getAuthorIdList());
        Book book = new Book();
        book.setAuthors(authors);
        book.setCategory(category);
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        bookRepository.save(book);
        return ApiResponse.default_CREATED(book);
    }

    @Override
    public ApiResponse<Book> updateBook(int id, BookDto bookDto) {
        Book book = getBookById(id).getData();
        Category category = categoryService.getCategoryById(bookDto.getCategoryId()).getData();
        Set<Author> authors = authorService.getAuthorByIds(bookDto.getAuthorIdList());
        book.setAuthors(authors);
        book.setCategory(category);
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        bookRepository.save(book);
        return ApiResponse.default_ACCEPTED(book);
    }

    @Override
    public ApiResponse<?> deleteBook(int id) {
        bookRepository.deleteById(id);
        return ApiResponse.default_NO_CONTENT();
    }

}
