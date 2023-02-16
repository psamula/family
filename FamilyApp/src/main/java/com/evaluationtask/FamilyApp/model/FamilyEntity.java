package com.evaluationtask.FamilyApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Getter
@Setter
@Table(name="family")
public class FamilyEntity {
    @Id
    private Long id;
    private String name;
    private int nrofinfants;
    private int nrofchildren;
    private int nrofadults;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyEntity that = (FamilyEntity) o;
        return nrofinfants == that.nrofinfants
                && nrofchildren == that.nrofchildren
                && nrofadults == that.nrofadults
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nrofinfants, nrofchildren, nrofadults);
    }
}
