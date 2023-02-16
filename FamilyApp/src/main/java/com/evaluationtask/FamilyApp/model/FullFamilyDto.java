package com.evaluationtask.FamilyApp.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class FullFamilyDto {
    FamilyEntity family;
    List<FamilyMember> familyMembers;

    public FullFamilyDto(FamilyEntity familyEntity, List<FamilyMember> familyMembers) {
        this.family = familyEntity;
        this.familyMembers = familyMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullFamilyDto that = (FullFamilyDto) o;
        return Objects.equals(family, that.family) && Objects.equals(familyMembers, that.familyMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(family, familyMembers);
    }
}
