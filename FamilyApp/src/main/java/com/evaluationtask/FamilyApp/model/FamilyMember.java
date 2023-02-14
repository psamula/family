package com.evaluationtask.FamilyApp.model;

import lombok.Data;

@Data
public class FamilyMember {
    private Long id;
    private Long familyid;
    private String firstname;
    private String lastname;
    private Long socialnumber;
}