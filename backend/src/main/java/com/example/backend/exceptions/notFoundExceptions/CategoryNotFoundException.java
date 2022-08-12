package com.example.backend.exceptions.notFoundExceptions;

public class CategoryNotFoundException extends NotFoundExcepiton {

    public CategoryNotFoundException(int id) {
        super(String.format("Category with %s id could not found!", id));
    }

}
