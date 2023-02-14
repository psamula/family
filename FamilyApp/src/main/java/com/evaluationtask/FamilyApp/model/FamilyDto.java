package com.evaluationtask.FamilyApp.model;

import lombok.Data;

import java.util.List;

@Data
public class FamilyDto {
    private String familyName;
    private Integer nrOfInfants;
    private Integer nrOfChildren;
    private Integer nrOfAdults;
    private List<FamilyMemberDto> familyMemberDtos;
}
