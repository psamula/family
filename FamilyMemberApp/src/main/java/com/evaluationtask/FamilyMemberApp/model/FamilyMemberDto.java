package com.evaluationtask.FamilyMemberApp.model;

import lombok.Data;

@Data
public class FamilyMemberDto {
    private String firstName;
    private String lastName;
    private int age;
    private Long socialNumber;
}
