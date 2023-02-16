package com.evaluationtask.FamilyMemberApp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Data
@Builder
@Table(name="familymember")
public class FamilyMemberEntity {
    @Id
    private Long id;
    private Long familyid;
    private String firstname;
    private String lastname;
    private int age;
    private Long socialnumber;  // Added unique social number as effective way of dealing with duplicates

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMemberEntity that = (FamilyMemberEntity) o;
        return age == that.age &&
                Objects.equals(familyid, that.familyid) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(socialnumber, that.socialnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyid, firstname, lastname, age, socialnumber);
    }

}
