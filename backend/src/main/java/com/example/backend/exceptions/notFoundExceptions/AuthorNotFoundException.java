package com.example.backend.exceptions.notFoundExceptions;

public class AuthorNotFoundException extends NotFoundExcepiton {

    public AuthorNotFoundException(int id) {
        super(String.format("AuthorId: %s could not find!", id));
    }

}
