package com.evaluationtask.FamilyApp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int errorCode;
    private String message;
}