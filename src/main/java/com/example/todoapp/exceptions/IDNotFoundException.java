package com.example.todoapp.exceptions;

public class IDNotFoundException extends RuntimeException{
    public IDNotFoundException(String message) {
        super(message);
    }
}
