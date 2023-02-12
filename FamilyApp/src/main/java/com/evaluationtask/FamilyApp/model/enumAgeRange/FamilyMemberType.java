package com.evaluationtask.FamilyApp.model.enumAgeRange;

public enum FamilyMemberType {
    INFANT(new AgeRange(0, 4)),
    CHILD(new AgeRange(5, 16)),
    ADULT(new AgeRange(17, 199));

    private AgeRange ageRange;

    FamilyMemberType(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }
}
