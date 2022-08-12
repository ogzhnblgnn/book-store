package com.example.backend.exceptions.notFoundExceptions;

public class BookNotFoundException extends NotFoundExcepiton {

    public BookNotFoundException(int id) {
        super(String.format("BookId: %s could not find!", id));
    }

}
