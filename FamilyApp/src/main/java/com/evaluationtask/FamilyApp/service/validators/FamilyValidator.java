package com.evaluationtask.FamilyApp.service.validators;

import com.evaluationtask.FamilyApp.exceptions.InvalidAgeException;
import com.evaluationtask.FamilyApp.exceptions.InvalidMembersAmountException;
import com.evaluationtask.FamilyApp.exceptions.ValidationException;
import com.evaluationtask.FamilyApp.model.FamilyDto;
import com.evaluationtask.FamilyApp.model.FamilyMemberDto;
import com.evaluationtask.FamilyApp.model.enumAgeRange.FamilyMemberType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyValidator implements IValidator<FamilyDto>{
    private final LinkedList<String> validationMessages = new LinkedList<>();

    @Override
    public boolean validate(FamilyDto toValidate, int nrOfInfants, int nrOfChildren, int nrOfAdults)
            throws ValidationException {
        try {
            this.validateFamilyData(toValidate, nrOfInfants, nrOfChildren, nrOfAdults);
            return true;
        }
        catch (ValidationException ex) {
            throw new ValidationException (this.validationMessages.getLast(), ex);
        }
    }
    public boolean validateFamilyData(FamilyDto toValidate, int nrOfInfants, int nrOfChildren, int nrOfAdults) {
        List<FamilyMemberDto> familyMemberDtos = toValidate.getFamilyMemberDtos();
        if (familyMemberDtos.stream()
                .anyMatch(f -> f.getAge() < 0)) {
            this.validationMessages.add("Age cannot be a negative number");
            throw new InvalidAgeException(this.validationMessages.getLast());
        }
        var memberTypesMap = familyMemberDtos.stream()
                .collect(Collectors.groupingBy(this::getMemberTypeByAge));

        if (memberTypesMap.getOrDefault(FamilyMemberType.INFANT, new ArrayList<>()).size() != nrOfInfants ||
            memberTypesMap.getOrDefault(FamilyMemberType.CHILD, new ArrayList<>()).size() != nrOfChildren ||
            memberTypesMap.getOrDefault(FamilyMemberType.ADULT, new ArrayList<>()).size() != nrOfAdults) {

            this.validationMessages.add("Declared number of member types in family is incorrect");
            throw new InvalidMembersAmountException(this.validationMessages.getLast());
        }
        return true;
    }
    private FamilyMemberType getMemberTypeByAge(FamilyMemberDto familyMemberDto) throws ValidationException {
        for (var memberType : FamilyMemberType.values()) {
            if (memberType.getAgeRange()
                    .withinAgeRange(familyMemberDto.getAge())) {
                return memberType;
            }
        }
        this.validationMessages.add("An age of one of the given family members is incorrect Accepted values: 0-199");
        throw new InvalidAgeException(validationMessages.getLast());
    }

    @Override
    public List<String> getValidationMessages() {
        return this.validationMessages;
    }
}
