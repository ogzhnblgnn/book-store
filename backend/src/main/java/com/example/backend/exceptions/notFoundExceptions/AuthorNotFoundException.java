package com.example.backend.exceptions.notFoundExceptions;

public class AuthorNotFoundException extends NotFoundExcepiton {

    public AuthorNotFoundException(int id) {
        super(String.format("Author with %s id could not found!", id));
    }

}
