package com.evaluationtask.FamilyMemberApp.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
public class FamilyMemberTestModel {
    @JsonIgnore
    private Long id;
    private Long familyid;
    private String firstname;
    private String lastname;
    private Long socialnumber;
}
