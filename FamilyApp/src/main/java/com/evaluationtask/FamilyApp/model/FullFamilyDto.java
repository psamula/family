package com.evaluationtask.FamilyApp.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class FullFamilyDto {
    FamilyEntity family;
    List<FamilyMember> familyMembers;

    public FullFamilyDto(FamilyEntity familyEntity, List<FamilyMember> familyMembers) {
        this.family = familyEntity;
        this.familyMembers = familyMembers;
    }
}
