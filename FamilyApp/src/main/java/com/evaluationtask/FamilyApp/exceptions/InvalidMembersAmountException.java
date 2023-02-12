package com.evaluationtask.FamilyApp.exceptions;

public class InvalidMembersAmountException extends ValidationException {
    public InvalidMembersAmountException(String message) {
        super(message);
    }

    public InvalidMembersAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
