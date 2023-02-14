package com.evaluationtask.FamilyApp.exceptions;

public class FamilyMemberAppException extends RuntimeException {
    public FamilyMemberAppException(String message) {
        super(message);
    }

    public FamilyMemberAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
