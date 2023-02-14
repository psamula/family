package com.evaluationtask.FamilyApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyMemberDto {
    private String firstName;
    private String lastName;
    private int age;
    private Long socialNumber;
}
