package com.evaluationtask.FamilyApp.exceptions;

public class IntercomponentException extends RuntimeException {
    public IntercomponentException(String message) {
        super(message);
    }

    public IntercomponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntercomponentException(Throwable cause) {
        super(cause);
    }
}
