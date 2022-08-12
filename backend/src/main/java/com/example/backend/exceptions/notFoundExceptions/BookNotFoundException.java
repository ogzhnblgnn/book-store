package com.example.backend.exceptions.notFoundExceptions;

public class BookNotFoundException extends NotFoundExcepiton {

    public BookNotFoundException(int id) {
        super(String.format("Book with %s id could not found!", id));
    }

}
