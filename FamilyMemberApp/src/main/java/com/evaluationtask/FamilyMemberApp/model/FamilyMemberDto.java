package com.evaluationtask.FamilyMemberApp.model;

import lombok.Data;

import java.util.Objects;

@Data
public class FamilyMemberDto {
    private String firstName;
    private String lastName;
    private int age;
    private Long socialNumber;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMemberDto that = (FamilyMemberDto) o;
        return Objects.equals(firstName, that.getFirstName()) &&
                Objects.equals(lastName, that.getLastName()) &&
                Objects.equals(age, that.getAge()) &&
                Objects.equals(socialNumber, that.getSocialNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, socialNumber);
    }
}
