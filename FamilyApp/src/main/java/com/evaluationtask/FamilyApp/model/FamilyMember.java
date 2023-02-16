package com.evaluationtask.FamilyApp.model;

import lombok.Data;

import java.util.Objects;

@Data
public class FamilyMember {
    private Long id;
    private Long familyid;
    private int age;
    private String firstname;
    private String lastname;
    private Long socialnumber;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMember that = (FamilyMember) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(socialnumber, that.socialnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyid, firstname, age, lastname, socialnumber);
    }


}