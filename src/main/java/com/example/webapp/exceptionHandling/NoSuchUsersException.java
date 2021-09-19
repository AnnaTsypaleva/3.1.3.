package com.example.webapp.exceptionHandling;

public class NoSuchUsersException extends RuntimeException {
    public NoSuchUsersException(String message) {
        super(message);
    }
}
