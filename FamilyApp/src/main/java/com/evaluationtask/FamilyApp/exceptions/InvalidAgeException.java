package com.evaluationtask.FamilyApp.exceptions;

public class InvalidAgeException extends ValidationException {
    public InvalidAgeException(String message) {
        super(message);
    }

    public InvalidAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
