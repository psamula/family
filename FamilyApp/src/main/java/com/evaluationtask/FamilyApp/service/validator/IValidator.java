package com.evaluationtask.FamilyApp.service.validator;

import com.evaluationtask.FamilyApp.exceptions.ValidationException;

import java.util.List;

public interface IValidator<T> {
    boolean validate(List<T> toValidate, int nrOfInfants, int nrOfChildren, int nrOfAdults) throws ValidationException;
    List<String> getValidationMessages();
}