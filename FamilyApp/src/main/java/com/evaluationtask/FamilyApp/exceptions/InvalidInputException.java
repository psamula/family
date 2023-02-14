package com.evaluationtask.FamilyApp.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }

    public InvalidInputException(String message) {
        super(message);
    }
}