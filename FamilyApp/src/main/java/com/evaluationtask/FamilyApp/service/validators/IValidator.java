package com.evaluationtask.FamilyApp.service.validators;

import com.evaluationtask.FamilyApp.exceptions.ValidationException;

import java.util.List;

public interface IValidator<T> {
    boolean validate(T toValidate, int nrOfInfants, int nrOfChildren, int nrOfAdults) throws ValidationException;
    List<String> getValidationMessages();
}