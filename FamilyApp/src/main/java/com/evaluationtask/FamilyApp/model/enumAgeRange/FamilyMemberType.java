package com.evaluationtask.FamilyApp.model.enumAgeRange;

public enum FamilyMemberType {
    INFANT(new AgeRange(0, 3)),
    CHILD(new AgeRange(4, 15)),
    ADULT(new AgeRange(16, 199));

    private AgeRange ageRange;

    FamilyMemberType(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }
}
