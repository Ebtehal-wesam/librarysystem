package com.example.librarysystem.Exceptions;

public class BookNotFoundExcption extends RuntimeException {
    public BookNotFoundExcption(String message) {
        super(message);
    }
}
