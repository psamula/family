package com.evaluationtask.FamilyApp.model.enumAgeRange;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AgeRange {
    final int minAge;
    final int maxAge;

    // Check whether the age of family member is within the age range of specific family member type
    public boolean withinAgeRange(int age) {
        return age >= this.minAge && age <= this.maxAge;
    }
}
