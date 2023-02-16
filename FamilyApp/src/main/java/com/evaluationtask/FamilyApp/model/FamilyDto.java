package com.evaluationtask.FamilyApp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class FamilyDto {
    private String familyName;
    private Integer nrOfInfants;
    private Integer nrOfChildren;
    private Integer nrOfAdults;
    private List<FamilyMemberDto> familyMemberDtos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyDto that = (FamilyDto) o;

        return Objects.equals(familyName, that.getFamilyName()) &&
                Objects.equals(nrOfInfants, that.getNrOfInfants()) &&
                Objects.equals(nrOfChildren, that.getNrOfChildren()) &&
                Objects.equals(nrOfAdults, that.getNrOfAdults()) &&
                this.familyMemberDtos.equals(that.familyMemberDtos);
    }
    public static FamilyDto ofFamilyEntity(FamilyEntity familyEntity) {
        FamilyDto familyDto = new FamilyDto();
        familyDto.setFamilyName(familyEntity.getName());
        familyDto.setNrOfInfants(familyEntity.getNrofinfants());
        familyDto.setNrOfChildren(familyEntity.getNrofchildren());
        familyDto.setNrOfAdults(familyEntity.getNrofadults());
        return familyDto;
    }
}
