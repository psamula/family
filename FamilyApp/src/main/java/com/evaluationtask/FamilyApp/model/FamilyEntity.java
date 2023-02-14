package com.evaluationtask.FamilyApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
}
