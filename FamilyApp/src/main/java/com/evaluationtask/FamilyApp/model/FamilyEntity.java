package com.evaluationtask.FamilyApp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "Family")
public class FamilyEntity {
    @Id
    private Long id;
    private String name;
    private int nrOfInfants;
    private int nrOfChildren;
    private int nrOfAdults;
}
