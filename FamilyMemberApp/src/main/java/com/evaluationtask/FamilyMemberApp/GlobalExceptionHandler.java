package com.evaluationtask.FamilyMemberApp;

import com.evaluationtask.FamilyMemberApp.exceptions.DuplicateEntityException;
import com.evaluationtask.FamilyMemberApp.exceptions.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEntityException(DuplicateEntityException ex) {
        ErrorResponse error = new ErrorResponse();
        var httpStatus = HttpStatus.CONFLICT;
        error.setErrorCode(httpStatus.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        ErrorResponse error = new ErrorResponse();
        var httpStatus = HttpStatus.BAD_REQUEST;
        error.setErrorCode(httpStatus.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse error = new ErrorResponse();
        var httpStatus = HttpStatus.NOT_FOUND;
        error.setErrorCode(httpStatus.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, httpStatus);
    }
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ErrorResponse> handlePSQLException(PSQLException ex) {
        ErrorResponse error = new ErrorResponse();
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        error.setErrorCode(httpStatus.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, httpStatus);
    }
}