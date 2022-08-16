package com.example.backend.exceptions.notFoundExceptions;

public class UserNotFoundException extends NotFoundExcepiton {
    public UserNotFoundException(int id) {
        super(String.format("User with %s id could not found", id));
    }
}
