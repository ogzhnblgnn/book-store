package com.example.backend.exceptions.notFoundExceptions;

public class CategoryNotFoundException extends NotFoundExcepiton {

    public CategoryNotFoundException(int id) {
        super(String.format("CategoryId: %s could not find!", id));
    }

}
